package com.project.todolist.controller;

import com.project.todolist.dto.request.LoginRequest;
import com.project.todolist.dto.request.RegisterRequest;
import com.project.todolist.dto.response.FindUserInfoResponse;
import com.project.todolist.dto.response.TokensResponse;
import com.project.todolist.service.user.AuthService;
import com.project.todolist.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register") //user 가입
    public void register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @GetMapping("/user") //user 정보조회
    public FindUserInfoResponse findMyInfo() {
        return userService.findMyInfo();
    }

    @GetMapping("/user/{userId}") //user 정보조회
    public FindUserInfoResponse findUserInfo(@PathVariable String userId) {
        return userService.findUserInfo(userId);
    }

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