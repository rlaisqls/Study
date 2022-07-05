package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponResponse;
import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.types.CouponDiscountType;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.global.exception.order.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    @Override
    public CreateCouponResponse createCoupon(CreateCouponRequest request) {

        Coupon coupon = Coupon
                .builder()
                .couponName(request.getName())
                .discountType(CouponDiscountType.valueOf(request.getDiscountType()))
                .expirationDate(LocalDateTime.now().plusMinutes(request.getExpirationTime()))
                .build();

        couponRepository.save(coupon);

        return new CreateCouponResponse(coupon.getId().toString());
    }

    @Override
    public FindCouponResponse findOneCoupon(UUID couponId) {

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        return FindCouponResponse.of(coupon);
    }
}