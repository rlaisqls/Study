package com.practice.shoppingmall.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemResponse {
    private String itemName;
    private int count;
}