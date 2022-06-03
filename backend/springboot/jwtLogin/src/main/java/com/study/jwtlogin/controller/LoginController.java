package com.study.jwtlogin.controller;

import com.study.jwtlogin.dto.LoginDto;
import com.study.jwtlogin.dto.TokensDto;
import com.study.jwtlogin.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public TokensDto login(@Valid @RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    @PutMapping("/reissue")
    public TokensDto reissue(@RequestHeader(name = "X-Refresh-Token") String refreshToken) {
        System.out.println("reissue 할떄 쓸 리프레시 토큰: "+refreshToken);
        return loginService.reissue(refreshToken);
    }
}