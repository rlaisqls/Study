package com.practice.shoppingmall.domain.user.presentation;

import com.practice.shoppingmall.domain.user.presentation.dto.request.LoginUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SignUpUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.SignUpUserResponse;
import com.practice.shoppingmall.domain.user.presentation.dto.response.TokenResponse;
import com.practice.shoppingmall.domain.user.service.auth.UserAuthService;
import com.practice.shoppingmall.domain.user.presentation.dto.request.EmailAuthenticationRequest;
import com.practice.shoppingmall.domain.user.service.auth.UserSignUpService;
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

    @PostMapping("/mail")
    public void mail(@Valid @RequestBody EmailAuthenticationRequest request) {
        userSignUpService.sendAuthenticationEmail(request);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpUserResponse signUp(@Valid @RequestBody SignUpUserRequest request) {
        return userSignUpService.doSignUpUser(request);
    }

    private final UserAuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginUserRequest request){
        return authService.login(request);
    }

    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.reissue(refreshToken);
    }
}