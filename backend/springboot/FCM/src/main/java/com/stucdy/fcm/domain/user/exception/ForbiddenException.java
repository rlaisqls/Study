package com.stucdy.fcm.domain.user.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class ForbiddenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenException();
    private ForbiddenException(){
        super(ErrorCode.FORBIDDEN);
    }
}