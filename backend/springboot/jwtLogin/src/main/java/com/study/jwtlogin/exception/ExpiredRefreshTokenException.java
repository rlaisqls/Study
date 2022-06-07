package com.study.jwtlogin.exception;

import com.study.jwtlogin.error.exception.BusinessException;
import com.study.jwtlogin.error.exception.ErrorCode;

public class ExpiredRefreshTokenException extends BusinessException {
    public ExpiredRefreshTokenException(){
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}