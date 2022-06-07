package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public UserAlreadyExistException(){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}