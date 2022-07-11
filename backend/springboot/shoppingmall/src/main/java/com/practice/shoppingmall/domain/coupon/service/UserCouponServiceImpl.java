package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.coupon.facade.CouponFacade;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponGroupResponse;
import com.practice.shoppingmall.domain.order.exception.OrderNotFoundException;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCouponServiceImpl implements UserCouponService{

    private final UserFacade userFacade;

    private final CouponRepository couponRepository;

    private final CouponFacade couponFacade;

    private final UserCouponRepository userCouponRepository;

    @Override
    public FindCouponGroupResponse findMyCoupons() {

        User user = userFacade.nowUser();

        List<UserCoupon> userCoupons =  userCouponRepository.findByUser(user)
                .stream()
                .filter(couponFacade::validateCoupon)
                .collect(Collectors.toList());

        return FindCouponGroupResponse.of(userCoupons);
    }

    @Override
    public void addMyCoupon(Long couponId) {

        User user = userFacade.nowUser();

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