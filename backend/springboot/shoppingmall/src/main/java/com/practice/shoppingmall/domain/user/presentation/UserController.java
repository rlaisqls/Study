package com.practice.shoppingmall.domain.user.presentation;

import com.practice.shoppingmall.domain.user.presentation.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.FindUserResponse;
import com.practice.shoppingmall.domain.user.service.UserService;
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

    @GetMapping("/user")
    public FindUserResponse getMyInfo() {
        return userService.getMyInfo();
    }

    @PatchMapping("/user")
    public void patchPassword(@Valid @RequestBody PasswordChangeRequest request) {
        userService.changePassword(request);
    }
}