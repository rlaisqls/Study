package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserAlreadyExistException();
    public UserAlreadyExistException(){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}