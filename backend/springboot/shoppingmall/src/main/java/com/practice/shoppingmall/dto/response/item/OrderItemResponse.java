package com.practice.shoppingmall.dto.response.item;

import com.practice.shoppingmall.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemResponse implements Response {
    private String itemName;
    private int count;
}