package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.OrderItemRequest;
import com.practice.shoppingmall.entity.delivery.Delivery;
import com.practice.shoppingmall.entity.delivery.DeliveryRepository;
import com.practice.shoppingmall.entity.delivery.DeliveryStatus;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.entity.order.Order;
import com.practice.shoppingmall.entity.order.OrderItem;
import com.practice.shoppingmall.entity.order.OrderStatus;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.exception.item.ItemNotFoundException;
import com.practice.shoppingmall.global.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserFacade userFacade;

    private final ItemRepository itemRepository;

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void order(List<OrderItemRequest> itemsRequest) {
        User user = userFacade.nowUser();

        Order order = Order.builder()
                .delivery(deliveryStart(user))
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER)
                .build();

        itemsRequest
                .stream()
                .map(itemRequest -> {
                    Item item = itemRepository.findById(itemRequest.getUuid())
                            .orElseThrow(() -> ItemNotFoundException.EXCEPTION);
                    return new OrderItem(item, itemRequest.getCount());
                })
                .forEach(order::putOrderItem);
    }

    public Delivery deliveryStart(User user) {
        return deliveryRepository.save(Delivery.builder()
                .address(user.getAddress())
                .deliveryStatus(DeliveryStatus.READY).build());
    }

}