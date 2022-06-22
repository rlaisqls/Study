package com.practice.shoppingmall.exception;


import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;

public class HandleAccessDeniedException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    public HandleAccessDeniedException(){
        super(ErrorCode.HANDLE_ACCESS_DENIED);
    }
}