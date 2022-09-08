package com.stucdy.fcm.domain.user.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    private PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}