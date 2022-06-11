package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}