package com.practice.shoppingmall.domain.user.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class UnVerifiedEmailException extends BusinessException {
    public static final BusinessException EXCEPTION = new UnVerifiedEmailException();
    private UnVerifiedEmailException(){
        super(ErrorCode.UNVERIFIED_EMAIL);
    }
}