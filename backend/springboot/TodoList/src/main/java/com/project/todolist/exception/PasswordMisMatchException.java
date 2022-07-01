package com.project.todolist.exception;

import com.project.todolist.error.exception.BusinessException;
import com.project.todolist.error.exception.ErrorCode;

public class PasswordMisMatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMisMatchException();
    public PasswordMisMatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}