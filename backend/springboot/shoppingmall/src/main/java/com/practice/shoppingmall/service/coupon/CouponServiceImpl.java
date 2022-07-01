package com.practice.shoppingmall.service.coupon;

import com.practice.shoppingmall.dto.request.coupon.CreateCouponRequest;
import com.practice.shoppingmall.dto.response.coupon.CreateCouponResponse;
import com.practice.shoppingmall.dto.response.coupon.FindCouponResponse;
import com.practice.shoppingmall.entity.coupon.Coupon;
import com.practice.shoppingmall.entity.coupon.CouponDiscountType;
import com.practice.shoppingmall.entity.coupon.CouponRepository;
import com.practice.shoppingmall.exception.order.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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