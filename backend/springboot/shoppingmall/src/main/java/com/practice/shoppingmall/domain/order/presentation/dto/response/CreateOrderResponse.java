package com.practice.shoppingmall.domain.order.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderResponse {
    Long orderId;
    int totalPrice;
}