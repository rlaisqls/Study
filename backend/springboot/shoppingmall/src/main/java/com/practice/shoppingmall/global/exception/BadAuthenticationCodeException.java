package com.practice.shoppingmall.global.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class BadAuthenticationCodeException extends BusinessException {
    public static final BusinessException EXCEPTION = new BadAuthenticationCodeException();
    private BadAuthenticationCodeException(){
        super(ErrorCode.BAD_AUTHENTICATION_CODE);
    }
}