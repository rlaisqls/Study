package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponResponse;

import java.util.UUID;

public interface CouponService {

    CreateCouponResponse createCoupon(CreateCouponRequest request);

    FindCouponResponse findOneCoupon(UUID id);
}