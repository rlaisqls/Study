package com.practice.shoppingmall.domain.coupon.facade;

import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.coupon.exception.CouponNotFoundException;
import com.practice.shoppingmall.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CouponFacade {
    private final UserCouponRepository userCouponRepository;

    public Boolean validateCoupon(UserCoupon userCoupon) {

        if (LocalDateTime.now().isAfter(userCoupon.getExpirationDate())) {
            System.out.println(userCoupon.getCoupon().getCouponName());
            userCouponRepository.delete(userCoupon);
            return false;
        }
        return true;
    }

    public UserCoupon getUserCoupon(Long userCouponId, User user) {

        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(() -> CouponNotFoundException.EXCEPTION);

        if (userCoupon.getUser() != user) throw CouponNotFoundException.EXCEPTION;

        return userCoupon;
    }
}