package com.practice.board.controller;

import com.practice.board.dto.request.LoginRequest;
import com.practice.board.dto.response.TokensResponse;
import com.practice.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public TokensResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PutMapping("/reissue")
    public TokensResponse reissue(@RequestHeader(name = "X-Refresh-Token") String refreshToken) {
        return authService.reissue(refreshToken);
    }
}