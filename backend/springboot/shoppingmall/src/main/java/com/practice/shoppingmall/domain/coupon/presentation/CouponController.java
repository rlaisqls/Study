package com.practice.shoppingmall.domain.coupon.presentation;

import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.service.CouponService;
import com.practice.shoppingmall.domain.coupon.service.UserCouponService;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponGroupResponse;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/coupon")
    public CreateCouponResponse createCoupon(CreateCouponRequest request){
        return couponService.createCoupon(request);
    }

    @GetMapping("/coupon/{couponId}")
    public FindCouponResponse findOneCoupon(@PathVariable UUID couponId){
        return couponService.findOneCoupon(couponId);
    }

    private final UserCouponService userCouponService;

    @GetMapping("/coupons")
    public FindCouponGroupResponse findMyCoupon(){
        return userCouponService.findMyCoupon();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/coupon/{couponId}")
    public void addMyCoupon(@PathVariable UUID couponId) {
        userCouponService.addMyCoupon(couponId);
    }

}