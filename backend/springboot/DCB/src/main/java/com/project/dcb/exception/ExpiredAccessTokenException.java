package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class ExpiredAccessTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredAccessTokenException();
    public ExpiredAccessTokenException(){
        super(ErrorCode.EXPIRED_ACCESS_TOKEN);
    }
}