package com.stucdy.fcm.domain.project.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class UserNotInvitedException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotInvitedException();
    private UserNotInvitedException(){
        super(ErrorCode.USER_NOT_INVITED);
    }
}