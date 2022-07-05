package com.practice.shoppingmall.global.exception.user;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class ForbiddenUserException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenUserException();
    private ForbiddenUserException(){
        super(ErrorCode.FORBIDDEN_USER);
    }
}