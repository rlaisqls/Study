package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class ExpiredTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredTokenException();
    public ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}