package com.practice.shoppingmall.exception.user;


import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    public PasswordMismatchException(){ super(ErrorCode.PASSWORD_MISMATCH); }

}