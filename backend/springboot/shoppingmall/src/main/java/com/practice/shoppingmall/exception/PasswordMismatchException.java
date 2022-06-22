package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    public PasswordMismatchException(){ super(ErrorCode.PASSWORD_MISMATCH); }

}