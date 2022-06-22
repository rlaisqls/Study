package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.LoginRequest;
import com.practice.shoppingmall.dto.request.RegisterRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register") //user 가입
    public TokenResponse signup(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.reissue(refreshToken);
    }
}