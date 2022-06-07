package com.study.jwtlogin.exception;

import com.study.jwtlogin.error.exception.BusinessException;
import com.study.jwtlogin.error.exception.ErrorCode;

public class InvalidInformaionException extends BusinessException {
    public InvalidInformaionException(){
        super(ErrorCode.INVALID_INFORMATION);
    }
}