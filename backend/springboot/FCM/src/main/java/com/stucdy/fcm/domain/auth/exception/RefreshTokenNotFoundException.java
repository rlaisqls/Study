package com.stucdy.fcm.domain.auth.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RefreshTokenNotFoundException();
    private RefreshTokenNotFoundException(){
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}