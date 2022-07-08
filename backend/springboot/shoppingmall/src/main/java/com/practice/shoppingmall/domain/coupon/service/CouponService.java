package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;

public interface CouponService {

    CreateCouponResponse createCoupon(CreateCouponRequest request);
}