package com.example.ss.exception;

import com.example.ss.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleBoardNotExistException(BusinessException e){
        return new ResponseEntity<>(new ExceptionResponse(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}