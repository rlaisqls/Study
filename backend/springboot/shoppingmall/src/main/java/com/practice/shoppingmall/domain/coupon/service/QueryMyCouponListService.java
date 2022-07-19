package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.coupon.facade.CouponFacade;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.QueryUserCouponListResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyCouponListService {

    private final UserFacade userFacade;

    private final UserCouponRepository userCouponRepository;

    private final CouponFacade couponFacade;

    public QueryUserCouponListResponse execute() {

        User user = userFacade.getCurrentUser();

        List<UserCoupon> userCoupons =  userCouponRepository.findByUser(user)
                .stream()
                .filter(couponFacade::validateCoupon)
                .collect(Collectors.toList());

        return QueryUserCouponListResponse.of(userCoupons);
    }
}