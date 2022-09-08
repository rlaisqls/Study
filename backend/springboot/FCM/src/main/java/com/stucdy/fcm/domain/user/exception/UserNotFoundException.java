package com.stucdy.fcm.domain.user.exception;


import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}