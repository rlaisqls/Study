package com.practice.shoppingmall.domain.user.presentation;

import com.practice.shoppingmall.domain.user.presentation.dto.request.LoginUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SignUpUserRequest;
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
    public ResponseBody mail(@Valid @RequestBody EmailAuthenticationRequest request) {
        return ResponseBody.of(userSignUpService.sendAuthenticationEmail(request), HttpStatus.OK.value());
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBody signUp(@Valid @RequestBody SignUpUserRequest request) {
        return ResponseBody.of(userSignUpService.doSignUpUser(request), HttpStatus.CREATED.value());
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