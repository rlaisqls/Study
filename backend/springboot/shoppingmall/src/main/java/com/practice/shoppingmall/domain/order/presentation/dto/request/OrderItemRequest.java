package com.practice.shoppingmall.domain.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class OrderItemRequest {

    @NotNull(message = "item_id는 null을 허용하지 않습니다.")
    private Long itemId;

    @NotNull(message = "count는 null을 허용하지 않습니다.")
    private Integer count;

    Long userCouponId;
}