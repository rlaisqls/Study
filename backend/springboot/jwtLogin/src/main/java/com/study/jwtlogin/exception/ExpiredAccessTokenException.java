package com.study.jwtlogin.exception;

import com.study.jwtlogin.error.exception.BusinessException;
import com.study.jwtlogin.error.exception.ErrorCode;

public class ExpiredAccessTokenException extends BusinessException {
    public ExpiredAccessTokenException(){
        super(ErrorCode.EXPIRED_ACCESS_TOKEN);
    }
}