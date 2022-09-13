package com.stucdy.fcm.global.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class FCMException extends BusinessException {
    public static final BusinessException EXCEPTION = new FCMException();
    private FCMException() {
        super(ErrorCode.FCM_EXCEPTION);
    }
}