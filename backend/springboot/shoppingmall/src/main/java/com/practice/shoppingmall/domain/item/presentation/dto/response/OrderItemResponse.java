package com.practice.shoppingmall.domain.item.presentation.dto.response;

import com.practice.shoppingmall.domain.order.domain.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemResponse {
    private String itemName;
    private Integer count;

    public static OrderItemResponse of(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .itemName(orderItem.getItem().getName())
                .count(orderItem.getCount())
                .build();
    }
}