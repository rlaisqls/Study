package com.practice.shoppingmall.domain.user.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    private PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}