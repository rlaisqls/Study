package com.practice.shoppingmall.controller.user;

import com.practice.shoppingmall.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.dto.response.ResponseBody;
import com.practice.shoppingmall.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseBody getUserInfo() {
        return ResponseBody.of(userService.getUserInfo(), HttpStatus.OK.value());
    }

    @PatchMapping("/user")
    public void patchPassword(@Valid @RequestBody PasswordChangeRequest request) {
        userService.changePassword(request);
    }
}