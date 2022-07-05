package com.practice.shoppingmall.domain.coupon.presentation.dto.response;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.types.CouponDiscountType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class FindCouponResponse {
    private UUID id;

    private String name;

    private CouponDiscountType discountType;

    private int discountAmount;

    private LocalDateTime expirationDate;

    public static FindCouponResponse of(Coupon coupon) {
        return FindCouponResponse
                .builder()
                .name(coupon.getCouponName())
                .discountType(coupon.getDiscountType())
                .discountAmount(coupon.getDiscountAmount())
                .expirationDate(coupon.getExpirationDate())
                .build();
    }
}