package com.practice.shoppingmall.service.order;

import com.practice.shoppingmall.dto.request.order.OrderItemRequest;
import com.practice.shoppingmall.dto.request.order.OrderRequest;
import com.practice.shoppingmall.dto.response.order.CreateOrderResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderGroupResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderResponse;
import com.practice.shoppingmall.entity.delivery.Delivery;
import com.practice.shoppingmall.entity.delivery.DeliveryStatus;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.entity.order.Order;
import com.practice.shoppingmall.entity.order.OrderItem;
import com.practice.shoppingmall.entity.order.OrderRepository;
import com.practice.shoppingmall.entity.order.OrderStatus;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.exception.order.OrderNotFoundException;
import com.practice.shoppingmall.exception.item.ItemNotFoundException;
import com.practice.shoppingmall.exception.user.ForbiddenUserException;
import com.practice.shoppingmall.facade.UserFacade;
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