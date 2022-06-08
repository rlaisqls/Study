package com.practice.board.controller;


import com.practice.board.dto.request.LoginRequest;
import com.practice.board.dto.request.PasswordChangeRequest;
import com.practice.board.dto.response.UserInformationResponse;
import com.practice.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register") //user 가입
    public void signup(@Valid @RequestBody LoginRequest request) {
        userService.register(request);
    }

    @GetMapping("/user") //user 정보조회
    public UserInformationResponse getUserInfo() {
        return userService.getUserInfo();
    }

    @PatchMapping("/user") //user 정보 수정
    public void passwordChange(@Valid @RequestBody PasswordChangeRequest request) {
        userService.passwordChange(request);
    }

    @GetMapping("/admin") //admin 전체 유저 정보조회
    public List<UserInformationResponse> getAllUserInfo() {
        return userService.getAllUserInfo();
    }
}