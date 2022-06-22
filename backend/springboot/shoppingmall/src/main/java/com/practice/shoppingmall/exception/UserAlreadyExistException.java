package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserAlreadyExistException();
    private UserAlreadyExistException(){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}