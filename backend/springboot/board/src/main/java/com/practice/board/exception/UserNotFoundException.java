package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}