package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class InvalidInformationException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidInformationException();
    public InvalidInformationException(){
        super(ErrorCode.INVALID_INFORMATION);
    }
}