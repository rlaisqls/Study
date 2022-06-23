package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class InvalidInformationException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidInformationException();
    public InvalidInformationException(){
        super(ErrorCode.INVALID_INFORMATION);
    }
}