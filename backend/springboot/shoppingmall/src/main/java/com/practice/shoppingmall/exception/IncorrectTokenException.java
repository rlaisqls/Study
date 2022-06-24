package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class IncorrectTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new IncorrectTokenException();
    private IncorrectTokenException(){
        super(ErrorCode.INCORRECT_TOKEN);
    }
}