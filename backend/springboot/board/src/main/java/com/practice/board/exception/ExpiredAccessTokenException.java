package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class ExpiredAccessTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredAccessTokenException();
    public ExpiredAccessTokenException(){
        super(ErrorCode.EXPIRED_ACCESS_TOKEN);
    }
}