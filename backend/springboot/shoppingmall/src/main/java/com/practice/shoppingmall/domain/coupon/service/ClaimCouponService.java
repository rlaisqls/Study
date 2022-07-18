package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.order.exception.OrderNotFoundException;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ClaimCouponService {

    private final UserFacade userFacade;

    private final CouponRepository couponRepository;

    private final UserCouponRepository userCouponRepository;

    public void execute(Long couponId) {

        User user = userFacade.getCurrentUser();

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        userCouponRepository.save(UserCoupon
                .builder()
                .user(user)
                .coupon(coupon)
                .expirationDate(LocalDateTime.now().plusMinutes(coupon.getValidityPeriod()))
                .build()
        );
    }
}