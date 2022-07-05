package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderItemRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderGroupResponse;
import com.practice.shoppingmall.domain.order.presentation.dto.response.FindOrderResponse;
import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import com.practice.shoppingmall.domain.delivery.domain.types.DeliveryStatus;
import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderItem;
import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.order.domain.types.OrderStatus;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.global.exception.order.OrderNotFoundException;
import com.practice.shoppingmall.global.exception.item.ItemNotFoundException;
import com.practice.shoppingmall.global.exception.user.ForbiddenUserException;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserFacade userFacade;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public CreateOrderResponse doOrder(OrderRequest request) {

        User user = userFacade.nowUser();

        Order order = Order.builder()
                .user(user)
                .delivery(deliveryStart(user))
                .orderStatus(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();

        List<OrderItem> orderItems = request.getOrderItems()
                .stream()
                .map(orderItem -> getOrderItemOfOrder(orderItem, order))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        orderRepository.save(order);

        return new CreateOrderResponse(order.getId().toString());
    }

    private Delivery deliveryStart(User user) {

        return Delivery
                .builder()
                .address(user.getAddress())
                .deliveryStatus(DeliveryStatus.READY)
                .build();
    }

    private OrderItem getOrderItemOfOrder(OrderItemRequest orderItem, Order order) {

        Item item = itemRepository.findById(UUID.fromString(orderItem.getUuid()))
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        item.removeStock(orderItem.getCount());

        return OrderItem.builder()
                .order(order)
                .item(item)
                .count(orderItem.getCount())
                .orderPrice(item.getPrice()*orderItem.getCount())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public FindOrderResponse findOneOrder(UUID id) {

        User user = userFacade.nowUser();

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        if (!order.getUser().equals(user)) throw ForbiddenUserException.EXCEPTION;

        return FindOrderResponse.of(order);
    }

    @Override
    @Transactional(readOnly = true)
    public FindOrderGroupResponse findMyOrder(int page, int size) {

        User user = userFacade.nowUser();

        Pageable request = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findByUser(user, request);

        return FindOrderGroupResponse.of(orderPage);
    }

    @Override
    @Transactional
    public void cancelOrder(String id) {

        User user = userFacade.nowUser();

        Order order = orderRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        if (!order.getUser().equals(user)) throw ForbiddenUserException.EXCEPTION;

        order.cancel();
        orderRepository.save(order);
    }

}