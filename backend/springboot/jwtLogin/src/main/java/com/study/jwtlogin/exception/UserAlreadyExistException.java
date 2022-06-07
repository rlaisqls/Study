package com.study.jwtlogin.exception;

import com.study.jwtlogin.error.exception.BusinessException;
import com.study.jwtlogin.error.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyExistException extends BusinessException {
    public UserAlreadyExistException(){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}