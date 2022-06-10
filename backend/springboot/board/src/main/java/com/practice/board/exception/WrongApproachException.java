package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class WrongApproachException extends BusinessException {
    public static final BusinessException EXCEPTION = new WrongApproachException();
    public WrongApproachException() {
        super(ErrorCode.WRONG_APPROACH);
    }
}