package com.practice.shoppingmall.domain.user.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class UnVerifiedAuthCodeException extends BusinessException {
    public static final BusinessException EXCEPTION = new UnVerifiedAuthCodeException();
    private UnVerifiedAuthCodeException(){
        super(ErrorCode.UNVERIFIED_AUTH_CODE);
    }
}