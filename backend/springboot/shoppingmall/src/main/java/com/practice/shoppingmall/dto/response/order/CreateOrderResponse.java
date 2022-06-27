package com.practice.shoppingmall.dto.response.order;

import com.practice.shoppingmall.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrderResponse implements Response {
    String uuid;
}