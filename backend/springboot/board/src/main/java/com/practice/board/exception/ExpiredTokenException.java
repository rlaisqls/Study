package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class ExpiredTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredTokenException();
    public ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}