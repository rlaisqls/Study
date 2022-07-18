package com.practice.shoppingmall.domain.user.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}