package com.practice.shoppingmall.domain.coupon.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class DiscountOutOfRangeException extends BusinessException {
    public static final BusinessException EXCEPTION = new DiscountOutOfRangeException();
    private DiscountOutOfRangeException(){
        super(ErrorCode.DISCOUNT_OUT_OF_RANGE);
    }
}