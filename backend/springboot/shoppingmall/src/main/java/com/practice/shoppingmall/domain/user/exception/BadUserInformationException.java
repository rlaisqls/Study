package com.practice.shoppingmall.domain.user.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class BadUserInformationException extends BusinessException {
    public static final BusinessException EXCEPTION = new BadUserInformationException();
    private BadUserInformationException(){
        super(ErrorCode.BAD_USER_INFORMATION);
    }
}