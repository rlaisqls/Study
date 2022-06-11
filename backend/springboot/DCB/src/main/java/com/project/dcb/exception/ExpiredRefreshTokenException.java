package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class ExpiredRefreshTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredRefreshTokenException();
    public ExpiredRefreshTokenException(){
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}