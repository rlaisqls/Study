package com.practice.shoppingmall.domain.coupon.presentation.dto.request;

import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCouponRequest {

    @NotBlank
    private String name;

    @NotBlank
    private CouponDiscountType discountType;

    @NotBlank
    private Integer discountAmount;

    @NotBlank
    private Integer expirationTime;
}