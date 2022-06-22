package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.OrderItemRequest;
import com.practice.shoppingmall.dto.response.OrderItemResponse;
import com.practice.shoppingmall.entity.delivery.Delivery;
import com.practice.shoppingmall.entity.delivery.DeliveryStatus;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.entity.order.Order;
import com.practice.shoppingmall.entity.order.OrderItem;
import com.practice.shoppingmall.entity.order.OrderStatus;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.exception.ItemNotExistException;
import com.practice.shoppingmall.facade.UserFacade;
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
                            .orElseThrow(() -> ItemNotExistException.EXCEPTION);
                    return new OrderItem(item, itemRequest.getCount());
                })
                .forEach(order::putOrderItem);
    }


    public Delivery deliveryStart(User user) {
        return Delivery.builder()
                .address(user.getAddress())
                .deliveryStatus(DeliveryStatus.READY).build();
    }

}