package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponGroupResponse;

import java.util.UUID;

public interface UserCouponService {
    FindCouponGroupResponse findMyCoupon();

    void addMyCoupon(UUID couponId);
}