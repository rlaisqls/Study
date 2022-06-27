package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.OrderItemRequest;
import com.practice.shoppingmall.dto.response.order.CreateOrderResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderResponse;
import com.practice.shoppingmall.dto.response.item.OrderItemResponse;
import com.practice.shoppingmall.entity.delivery.Delivery;
import com.practice.shoppingmall.entity.delivery.DeliveryRepository;
import com.practice.shoppingmall.entity.delivery.DeliveryStatus;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.entity.order.Order;
import com.practice.shoppingmall.entity.order.OrderItem;
import com.practice.shoppingmall.entity.order.OrderRepository;
import com.practice.shoppingmall.entity.order.OrderStatus;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.exception.OrderNotFoundException;
import com.practice.shoppingmall.exception.item.ItemNotFoundException;
import com.practice.shoppingmall.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final UserFacade userFacade;

    private final ItemRepository itemRepository;

    private final DeliveryRepository deliveryRepository;

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public CreateOrderResponse order(List<OrderItemRequest> itemsRequest) {

        User user = userFacade.nowUser();

        Order order = Order.builder()
                .user(user)
                .delivery(deliveryStart(user))
                .orderItemList(new ArrayList<>())
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER)
                .build();

        order.setOrderItemList(itemsRequest
                .stream()
                .map(itemRequest -> {
                    Item item = itemRepository.findById(UUID.fromString(itemRequest.getUuid()))
                            .orElseThrow(() -> ItemNotFoundException.EXCEPTION);
                    return new OrderItem(order, item, itemRequest.getCount());
                })
                .collect(Collectors.toList()));

        return new CreateOrderResponse(orderRepository.save(order).getUuid().toString());
    }

    private Delivery deliveryStart(User user) {

        return Delivery
                .builder()
                .address(user.getAddress())
                .deliveryStatus(DeliveryStatus.READY)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public FindOrderResponse findOrder(String uuid){

        Order order = orderRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        List<OrderItemResponse> orderItemResponses = order.getOrderItemList()
                .stream()
                .map(orderItem -> new OrderItemResponse(orderItem.getItem().getName(),orderItem.getCount()))
                .collect(Collectors.toList());

        return FindOrderResponse.builder()
                .orderStatus(order.getOrderStatus().toString())
                .username(order.getUser().getUsername())
                .orderItemList(orderItemResponses)
                .build();
    }

}