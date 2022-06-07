package com.study.jwtlogin.error;

import com.study.jwtlogin.error.exception.BusinessException;
import com.study.jwtlogin.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j //로깅에 대한 추상레이어를 제공해줌
@RestControllerAdvice //controller 전체에 적용될 수 있도록 함 (RestController니까 응답을 Json으로 반환)
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception){
        final ErrorCode errorCode = exception.getErrorCode();
        log.warn("BusinessException : "+exception);
        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatusCode(),errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatusCode()));
    }
}