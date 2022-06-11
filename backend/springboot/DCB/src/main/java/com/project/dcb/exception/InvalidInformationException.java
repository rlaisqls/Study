package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class InvalidInformationException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidInformationException();
    public InvalidInformationException(){
        super(ErrorCode.INVALID_INFORMATION);
    }
}