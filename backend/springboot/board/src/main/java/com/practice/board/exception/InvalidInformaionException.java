package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class InvalidInformaionException extends BusinessException {
    public InvalidInformaionException(){
        super(ErrorCode.INVALID_INFORMATION);
    }
}