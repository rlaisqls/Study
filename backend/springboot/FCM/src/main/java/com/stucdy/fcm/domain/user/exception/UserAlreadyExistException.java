package com.stucdy.fcm.domain.user.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserAlreadyExistException();
    private UserAlreadyExistException(){
        super(ErrorCode.USER_ALREADY_EXIST);
    }
}