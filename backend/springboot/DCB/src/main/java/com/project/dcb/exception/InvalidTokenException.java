package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidTokenException();
    public InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}