package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    @Override
    public CreateCouponResponse createCoupon(CreateCouponRequest request) {

        Coupon coupon = couponRepository.save(Coupon.of(request));

        return CreateCouponResponse
                .builder()
                .id(coupon.getId())
                .build();
    }
}