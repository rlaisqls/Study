package com.practice.shoppingmall.util;

import com.practice.shoppingmall.constant.ItemConstant;
import com.practice.shoppingmall.constant.OrderConstant;
import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderItem;

import java.time.LocalDateTime;
import java.util.Collections;

public class OrderBuilder {
    public static OrderItem orderItemBuilder() {
        return OrderItem
                .builder()
                .id(OrderConstant.ORDER_ITEM_ID)
                .item(ItemBuilder.build())
                .count(OrderConstant.ORDER_ITEM_COUNT)
                .totalPrice(ItemConstant.PRICE * OrderConstant.ORDER_ITEM_COUNT)
                .build();
    }

    public static Order build() {
        return Order
                .builder()
                .id(OrderConstant.ID)
                .user(UserBuilder.build())
                .orderDate(LocalDateTime.of(2022,7,9,12,30))
                .orderItems(Collections.singletonList(orderItemBuilder()))
                .delivery(Delivery.start(UserBuilder.build()))
                .orderStatus(OrderConstant.ORDERSTATUS)
                .build();
    }
}