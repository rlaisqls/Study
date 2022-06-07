package com.study.jwtlogin.jwt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    /*Spring Security에서 인증과 접근제어는 RequestDispatcher에 의해 다른 서블릿으로 구분된다
    GenericFilterBean는 매 서블릿 마다 호출되기 때문에, GenericFilterBean을 상속받아서 구현하면 이 필터가 여러번 실행된다.
    반면에 OncePerRequestFilter는 요청 한번에 딱 한 번만 실행된다.
    얘는 한 번 실행하면 되기 때문에 OncePerRequestFilter를 사용하는게 적절하다.*/
    private final TokenProvider tokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //토큰에서 권한정보 받아온 후 그대로 설정해줌
        String token = tokenProvider.resolveToken(servletRequest);
        if (token != null) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}