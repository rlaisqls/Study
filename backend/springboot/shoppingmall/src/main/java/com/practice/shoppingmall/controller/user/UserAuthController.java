package com.practice.shoppingmall.controller.user;

import com.practice.shoppingmall.dto.request.user.LoginUserRequest;
import com.practice.shoppingmall.dto.request.user.SignUpUserRequest;
import com.practice.shoppingmall.dto.response.ResponseBody;
import com.practice.shoppingmall.service.user.UserAuthService;
import com.practice.shoppingmall.service.user.UserSignUpService;
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
public class UserAuthController {

    private final UserSignUpService userSignUpService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseBody register(@Valid @RequestBody SignUpUserRequest request) {
        return ResponseBody.of(authService.register(request), HttpStatus.CREATED.value());
    }

    private final UserAuthService authService;

    @PostMapping("/login")
    public ResponseBody login(@Valid @RequestBody LoginUserRequest request){
        return ResponseBody.of(authService.login(request), HttpStatus.OK.value());
    }

    @PutMapping("/reissue")
    public ResponseBody reissue(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return ResponseBody.of(authService.reissue(refreshToken), HttpStatus.OK.value());
    }
}