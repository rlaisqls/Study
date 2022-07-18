package com.practice.shoppingmall.domain.auth.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RefreshTokenNotFoundException();
    private RefreshTokenNotFoundException(){
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}