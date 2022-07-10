package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponGroupResponse;

public interface UserCouponService {
    FindCouponGroupResponse findMyCoupons();

    void addMyCoupon(Long couponId);
}