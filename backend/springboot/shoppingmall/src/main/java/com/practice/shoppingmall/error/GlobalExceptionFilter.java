package com.practice.shoppingmall.error;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.shoppingmall.error.exception.BusinessException;
import com.practice.shoppingmall.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
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
        } catch (BusinessException e) {

            ErrorCode errorCode = e.getErrorCode();
            response.setStatus(errorCode.getStatus());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ErrorResponse errorResponse = ErrorResponse.of(errorCode, errorCode.getMessage());

            objectMapper.writeValue(response.getWriter(), errorResponse);
        }
    }
}