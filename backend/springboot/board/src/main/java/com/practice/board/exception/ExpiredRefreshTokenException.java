package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class ExpiredRefreshTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ExpiredRefreshTokenException();
    public ExpiredRefreshTokenException(){
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}