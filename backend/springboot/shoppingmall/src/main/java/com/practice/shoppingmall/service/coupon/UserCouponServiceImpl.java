package com.practice.shoppingmall.service.coupon;

import com.practice.shoppingmall.dto.response.coupon.FindCouponGroupResponse;
import com.practice.shoppingmall.dto.response.coupon.FindCouponResponse;
import com.practice.shoppingmall.dto.response.order.FindOrderResponse;
import com.practice.shoppingmall.entity.coupon.Coupon;
import com.practice.shoppingmall.entity.coupon.CouponRepository;
import com.practice.shoppingmall.entity.coupon.UserCoupon;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.order.OrderNotFoundException;
import com.practice.shoppingmall.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCouponServiceImpl implements UserCouponService{

    private final UserFacade userFacade;

    private final CouponRepository couponRepository;

    private final UserRepository userRepository;

    @Override
    public FindCouponGroupResponse findMyCoupon() {

        User user = userFacade.nowUser();

        List<Coupon> coupons =  user.getCoupons()
                .stream()
                .map(UserCoupon::getCoupon)
                .collect(Collectors.toList());

        return FindCouponGroupResponse.of(coupons);
    }

    @Override
    public void addMyCoupon(UUID couponId) {

        User user = userFacade.nowUser();

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);

        user.addCoupon(coupon);

        userRepository.save(user);
    }
}