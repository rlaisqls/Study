package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    public PasswordMismatchException(){ super(ErrorCode.PASSWORD_MISMATCH); }

}