package com.study.jwtlogin.exception;

import com.study.jwtlogin.error.exception.BusinessException;
import com.study.jwtlogin.error.exception.ErrorCode;

public class IncorrectTokenException extends BusinessException {
    public IncorrectTokenException(){
        super(ErrorCode.INCORRECT_TOKEN);
    }
}