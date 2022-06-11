package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class IncorrectTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new IncorrectTokenException();
    public IncorrectTokenException(){
        super(ErrorCode.INCORRECT_TOKEN);
    }
}