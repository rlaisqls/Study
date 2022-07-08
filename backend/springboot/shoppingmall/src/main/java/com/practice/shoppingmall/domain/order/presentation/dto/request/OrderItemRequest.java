package com.practice.shoppingmall.domain.order.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemRequest {
    @NotBlank(message = "상품 정보를 입력해주세요")
    private Long itemId;

    @NotBlank(message = "구매 수량을 입력해주세요")
    private int count;

    @Nullable
    Long couponId;
}