package com.stucdy.fcm.global.exception;


import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class ExpiredTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredTokenException();
    private ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}