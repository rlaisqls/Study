package com.practice.shoppingmall.domain.coupon.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class CouponNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new CouponNotFoundException();
    private CouponNotFoundException(){
        super(ErrorCode.COUPON_NOT_FOUND);
    }
}