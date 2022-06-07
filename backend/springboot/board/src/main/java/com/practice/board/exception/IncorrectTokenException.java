package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class IncorrectTokenException extends BusinessException {
    public IncorrectTokenException(){
        super(ErrorCode.INCORRECT_TOKEN);
    }
}