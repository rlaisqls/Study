package com.study.oopspring.web;

import com.study.oopspring.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SetUrlInterceptor implements HandlerInterceptor {
    private final MyLogger myLogger;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        return true;
    }
}