package com.project.dcb.security.jwt;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getCookies() != null) {
            Arrays.stream(servletRequest.getCookies())
                    .filter(cookie -> cookie.getName().equals("accessToken"))
                    .findFirst()
                    .map(Cookie::getValue).ifPresent(token -> SecurityContextHolder.getContext()
                            .setAuthentication(jwtTokenProvider.getAuthentication(token)));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}