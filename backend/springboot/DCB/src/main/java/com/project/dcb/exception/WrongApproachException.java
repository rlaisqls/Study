package com.project.dcb.exception;


import com.project.dcb.error.exception.BusinessException;
import com.project.dcb.error.exception.ErrorCode;

public class WrongApproachException extends BusinessException {
    public static final BusinessException EXCEPTION = new WrongApproachException();
    public WrongApproachException() {
        super(ErrorCode.WRONG_APPROACH);
    }
}