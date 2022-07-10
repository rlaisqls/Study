package com.practice.shoppingmall.domain.coupon.presentation.dto.request;

import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCouponRequest {

    @NotNull
    private String name;

    @NotNull
    private CouponDiscountType discountType;

    @NotNull
    private Integer discountAmount;

    @NotNull
    private Long validityPeriod;
}