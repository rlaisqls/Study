package com.practice.shoppingmall.exception;

import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class BadAuthenticationCodeException extends BusinessException {
    public static final BusinessException EXCEPTION = new BadAuthenticationCodeException();
    private BadAuthenticationCodeException(){
        super(ErrorCode.BAD_AUTHENTICATION_CODE);
    }
}