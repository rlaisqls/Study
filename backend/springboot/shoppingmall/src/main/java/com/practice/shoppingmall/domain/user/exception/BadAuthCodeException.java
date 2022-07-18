package com.practice.shoppingmall.domain.user.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class BadAuthCodeException extends BusinessException {
    public static final BusinessException EXCEPTION = new BadAuthCodeException();
    private BadAuthCodeException(){
        super(ErrorCode.BAD_AUTHENTICATION_CODE);
    }
}