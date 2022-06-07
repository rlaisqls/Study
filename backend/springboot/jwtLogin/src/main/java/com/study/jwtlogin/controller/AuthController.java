package com.study.jwtlogin.controller;

import com.study.jwtlogin.dto.LoginDto;
import com.study.jwtlogin.dto.TokensDto;
import com.study.jwtlogin.jwt.TokenProvider;
import com.study.jwtlogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public TokensDto login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PutMapping("/reissue")
    public TokensDto reissue(@RequestHeader(name = "X-Refresh-Token") String refreshToken) {
        return authService.reissue(refreshToken);
    }
}