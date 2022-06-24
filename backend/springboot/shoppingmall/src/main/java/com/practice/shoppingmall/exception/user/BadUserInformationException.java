package com.practice.shoppingmall.exception.user;

import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class BadUserInformationException extends BusinessException {
    public static final BusinessException EXCEPTION = new BadUserInformationException();
    private BadUserInformationException(){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}