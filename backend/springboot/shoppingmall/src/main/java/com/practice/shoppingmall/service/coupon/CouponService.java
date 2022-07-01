package com.practice.shoppingmall.service.coupon;

import com.practice.shoppingmall.dto.request.coupon.CreateCouponRequest;
import com.practice.shoppingmall.dto.response.coupon.CreateCouponResponse;
import com.practice.shoppingmall.dto.response.coupon.FindCouponResponse;

import java.util.UUID;

public interface CouponService {

    CreateCouponResponse createCoupon(CreateCouponRequest request);

    FindCouponResponse findOneCoupon(UUID id);
}