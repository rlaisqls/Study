package com.springboot.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionHandleController {
    @GetMapping("/jwt")
    public void JwtException(){
        throw new UserNotExistException();
    }
}