package com.practice.shoppingmall.global.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class InvalidParameterException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidParameterException();
    private InvalidParameterException(){
        super(ErrorCode.INVALID_PARAMETER);
    }
}