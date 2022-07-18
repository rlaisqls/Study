package com.practice.shoppingmall.domain.order.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemRequest {

    @NotNull(message = "구매할 상품을 입력해주세요")
    private Long itemId;

    @NotNull(message = "구매 수량을 입력해주세요")
    private int count;

    @Nullable
    Long userCouponId;
}