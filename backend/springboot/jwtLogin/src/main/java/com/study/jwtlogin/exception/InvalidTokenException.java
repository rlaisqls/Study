package com.study.jwtlogin.exception;

import com.study.jwtlogin.error.exception.BusinessException;
import com.study.jwtlogin.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}