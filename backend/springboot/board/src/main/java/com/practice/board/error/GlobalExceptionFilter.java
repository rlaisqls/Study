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

        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e){
            ErrorCode errorCode = e.getErrorCode();
            response.setStatus(errorCode.getStatusCode());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ErrorResponse errorResponse = ErrorResponse.of(errorCode, errorCode.getMessage());
            objectMapper.writeValue(response.getWriter(), errorResponse);
        } catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            response.setStatus(response.getStatus());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ErrorResponse errorResponse = ErrorResponse.of(response.getStatus(), e.getMessage());
            objectMapper.writeValue(response.getWriter(), errorResponse);
        }
    }
}