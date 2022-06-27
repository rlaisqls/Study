package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.dto.response.FindUserResponse;
import com.practice.shoppingmall.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user") //user 정보조회
    public FindUserResponse getUserInfo() {
        return userService.getUserInfo();
    }

    @PatchMapping("/user") //user 정보 수정
    public void changePassword(@Valid @RequestBody PasswordChangeRequest request) {
        userService.changePassword(request);
    }
}