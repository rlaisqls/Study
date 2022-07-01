package com.practice.shoppingmall.dto.response.coupon;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.coupon.Coupon;
import com.practice.shoppingmall.entity.coupon.CouponDiscountType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class FindCouponResponse implements Response {
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