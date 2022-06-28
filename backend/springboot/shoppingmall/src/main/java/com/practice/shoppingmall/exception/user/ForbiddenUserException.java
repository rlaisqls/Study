package com.practice.shoppingmall.exception.user;

import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class ForbiddenUserException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenUserException();
    private ForbiddenUserException(){
        super(ErrorCode.FORBIDDEN_USER);
    }
}