package com.stucdy.fcm.domain.auth.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RefreshTokenNotFoundException();
    private RefreshTokenNotFoundException(){
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}