package com.practice.shoppingmall.domain.coupon.presentation;

import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.FindCouponGroupResponse;
import com.practice.shoppingmall.domain.coupon.service.ClaimCouponService;
import com.practice.shoppingmall.domain.coupon.service.CreateCouponService;
import com.practice.shoppingmall.domain.coupon.service.QueryMyCouponListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final ClaimCouponService claimCouponService;

    private final CreateCouponService createCouponService;

    private final QueryMyCouponListService queryMyCouponListService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/coupon")
    public CreateCouponResponse claimCoupon(@Valid @RequestBody CreateCouponRequest request){
        return createCouponService.execute(request);
    }

    @GetMapping("/coupon")
    public FindCouponGroupResponse findMyCoupons(){
        return queryMyCouponListService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/coupon/{couponId}")
    public void claimCoupon(@PathVariable Long couponId){
        claimCouponService.execute(couponId);
    }

}