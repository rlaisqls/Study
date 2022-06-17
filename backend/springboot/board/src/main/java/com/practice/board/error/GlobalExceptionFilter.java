package com.practice.board.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.board.error.exception.BusinessException;
import com.practice.board.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*GlobalExceptionHandler에선 application에서 일어난 예외만 처리해주기 때문에 filter에서 일어난 예외는 따로 처리해줘야함.
        그렇기 때문이 에러를 처리하는 필터를 하나 추가해서 HttpServletResponse에 에러 정보를 담아서 반환함. */
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e){
            ErrorCode errorCode = e.getErrorCode();
            response.setStatus(errorCode.getStatusCode());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ErrorResponse errorResponse = ErrorResponse.of(errorCode, errorCode.getMessage());
            objectMapper.writeValue(response.getWriter(), errorResponse);
            /*
            ErrorCode errorCode = e.getErrorCode();
            final ErrorResponse errorResponse = ErrorResponse.of(errorCode,errorCode.getMessage());
            response.setStatus(errorCode.getStatusCode());
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.toString());*/
        }
    }
}