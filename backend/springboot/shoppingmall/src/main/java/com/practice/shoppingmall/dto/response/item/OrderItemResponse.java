package com.practice.shoppingmall.dto.response.item;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemResponse implements Response {
    private String itemName;
    private Integer count;

    public static OrderItemResponse of(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .itemName(orderItem.getItem().getName())
                .count(orderItem.getCount())
                .build();
    }
}