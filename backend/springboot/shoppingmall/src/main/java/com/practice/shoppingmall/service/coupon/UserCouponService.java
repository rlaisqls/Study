package com.practice.shoppingmall.service.coupon;

import com.practice.shoppingmall.dto.response.coupon.FindCouponGroupResponse;

import java.util.UUID;

public interface UserCouponService {
    FindCouponGroupResponse findMyCoupon();

    void addMyCoupon(UUID couponId);
}