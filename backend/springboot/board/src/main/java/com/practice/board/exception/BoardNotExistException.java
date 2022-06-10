package com.practice.board.exception;

import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;

public class BoardNotExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new BoardNotExistException();
    public BoardNotExistException(){
        super(ErrorCode.BOARD_NOT_EXIST);
    }
}