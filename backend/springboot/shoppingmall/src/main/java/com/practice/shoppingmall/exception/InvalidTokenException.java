package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidTokenException();
    public InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}