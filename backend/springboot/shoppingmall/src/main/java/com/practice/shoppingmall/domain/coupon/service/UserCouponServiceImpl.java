package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponGroupResponse;
import com.practice.shoppingmall.domain.order.exception.OrderNotFoundException;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCouponServiceImpl implements UserCouponService{

    private final UserFacade userFacade;

    private final CouponRepository couponRepository;

    private final UserRepository userRepository;

    @Override
    public FindCouponGroupResponse findMyCoupons() {

        User user = userFacade.nowUser();

<<<<<<< HEAD
        List<UserCoupon> coupons = new ArrayList<>(user.getCoupons());
=======
        List<UserCoupon> userCoupons =  user.getCoupons();
>>>>>>> main

        return FindCouponGroupResponse.of(userCoupons);
    }

    @Override
    public void addMyCoupon(Long couponId) {

        User user = userFacade.nowUser();

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        user.addCoupon(coupon);

        userRepository.save(user);
    }
}