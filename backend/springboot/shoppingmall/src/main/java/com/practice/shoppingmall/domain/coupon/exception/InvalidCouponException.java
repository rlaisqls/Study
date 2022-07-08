package com.practice.shoppingmall.domain.coupon.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class InvalidCouponException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidCouponException();
    private InvalidCouponException(){
        super(ErrorCode.INVALID_COUPON);
    }
}