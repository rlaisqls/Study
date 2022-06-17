package com.practice.board.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.board.error.ErrorResponse;
import com.practice.board.error.exception.ErrorCode;
import com.practice.board.exception.ExpiredTokenException;
import com.practice.board.exception.IncorrectTokenException;
import com.practice.board.exception.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        /*
        try{
            String token = jwtTokenProvider.resolveToken(request);
            if (token != null) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw IncorrectTokenException.EXCEPTION;
            //setResponse(response, ErrorCode.INCORRECT_TOKEN);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
            //setResponse(response, ErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
            //setResponse(response, ErrorCode.INVALID_TOKEN);
        }*/
    }

    private void setResponse(HttpServletResponse response, ErrorCode incorrectToken) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        final ErrorResponse errorResponse = ErrorResponse.of(incorrectToken, incorrectToken.getMessage());
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}