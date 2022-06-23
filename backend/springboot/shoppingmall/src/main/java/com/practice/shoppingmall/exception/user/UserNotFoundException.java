package com.practice.shoppingmall.exception.user;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}