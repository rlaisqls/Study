package com.practice.shoppingmall.domain.coupon.presentation;

import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponGroupResponse;
import com.practice.shoppingmall.domain.coupon.service.CouponService;
import com.practice.shoppingmall.domain.coupon.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/coupon")
    public CreateCouponResponse createCoupon(@RequestBody CreateCouponRequest request){
        return couponService.createCoupon(request);
    }

    private final UserCouponService userCouponService;

    @GetMapping("/coupon")
    public FindCouponGroupResponse findMyCoupons(){
        return userCouponService.findMyCoupons();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/coupon/{couponId}")
    public void addMyCoupon(@PathVariable Long couponId) {
        userCouponService.addMyCoupon(couponId);
    }

}