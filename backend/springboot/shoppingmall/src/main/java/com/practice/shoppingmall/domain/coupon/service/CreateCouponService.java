package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCouponService {

    private final CouponRepository couponRepository;

    public CreateCouponResponse execute(CreateCouponRequest request) {

        Coupon coupon = couponRepository.save(Coupon.of(request));

        return new CreateCouponResponse(coupon.getId());
    }
}