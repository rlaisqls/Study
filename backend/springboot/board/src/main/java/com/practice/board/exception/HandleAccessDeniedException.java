package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class HandleAccessDeniedException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    public HandleAccessDeniedException(){
        super(ErrorCode.HANDLE_ACCESS_DENIED);
    }
}