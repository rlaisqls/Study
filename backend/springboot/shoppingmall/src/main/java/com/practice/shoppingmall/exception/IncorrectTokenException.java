package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class IncorrectTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new IncorrectTokenException();
    public IncorrectTokenException(){
        super(ErrorCode.INCORRECT_TOKEN);
    }
}