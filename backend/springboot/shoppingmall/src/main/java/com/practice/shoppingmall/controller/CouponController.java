package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.coupon.CreateCouponRequest;
import com.practice.shoppingmall.dto.response.ResponseBody;
import com.practice.shoppingmall.service.coupon.CouponService;
import com.practice.shoppingmall.service.coupon.UserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
    public ResponseBody createCoupon(CreateCouponRequest request){
        return ResponseBody.of(couponService.createCoupon(request), HttpStatus.OK.value());
    }

    @GetMapping("/coupon/{couponId}")
    public ResponseBody findOneCoupon(@PathVariable UUID couponId){
        return ResponseBody.of(couponService.findOneCoupon(couponId), HttpStatus.OK.value());
    }

    private final UserCouponService userCouponService;

    @GetMapping("/coupons")
    public ResponseBody findMyCoupon(){
        return ResponseBody.of(userCouponService.findMyCoupon(), HttpStatus.OK.value());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/coupon/{couponId}")
    public void addMyCoupon(@PathVariable UUID couponId) {
        userCouponService.addMyCoupon(couponId);
    }

}