package com.practice.shoppingmall.domain.user.presentation;

import com.practice.shoppingmall.domain.user.presentation.dto.request.LoginUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SendMailRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SignUpUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.VerifyAuthCodeRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.QueryUserInfoResponse;
import com.practice.shoppingmall.domain.user.presentation.dto.response.SignUpUserResponse;
import com.practice.shoppingmall.domain.user.presentation.dto.response.TokenResponse;
import com.practice.shoppingmall.domain.user.service.ChangePasswordService;
import com.practice.shoppingmall.domain.user.service.QueryMyInfoService;
import com.practice.shoppingmall.domain.user.service.QueryUserInfoService;
import com.practice.shoppingmall.domain.user.service.SendEmailAuthCodeService;
import com.practice.shoppingmall.domain.user.service.UserLoginService;
import com.practice.shoppingmall.domain.user.service.UserSignUpService;
import com.practice.shoppingmall.domain.user.service.UserTokenRefreshService;
import com.practice.shoppingmall.domain.user.service.VerifyAuthCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final SendEmailAuthCodeService sendEmailAuthCodeService;

    private final VerifyAuthCodeService verifyAuthCodeService;

    private final UserSignUpService userSignUpService;

    private final UserLoginService userLoginService;

    private final UserTokenRefreshService userTokenRefreshService;

    private final ChangePasswordService changePasswordService;

    private final QueryMyInfoService queryMyInfoService;

    private final QueryUserInfoService queryUserInfoService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/mail/verify")
    public void sendEmailAuthCode(@Valid @RequestBody SendMailRequest request){
        sendEmailAuthCodeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/mail/verify")
    public void verifyAuthCode(@Valid @RequestBody VerifyAuthCodeRequest request) {
        verifyAuthCodeService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SignUpUserResponse signUp(@Valid @RequestBody SignUpUserRequest request) {
        return userSignUpService.execute(request);
    }

    @PostMapping("/auth")
    public TokenResponse login(@Valid @RequestBody LoginUserRequest request){
        return userLoginService.execute(request);
    }

    @PutMapping("/auth")
    public TokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return userTokenRefreshService.execute(refreshToken);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void changePassword(@Valid @RequestBody PasswordChangeRequest request){
        changePasswordService.execute(request);
    }

    @GetMapping
    public QueryUserInfoResponse queryMyInformation() {
        return queryMyInfoService.execute();
    }

    @GetMapping("/{user-id}")
    public QueryUserInfoResponse queryUserInformation(@PathVariable("user-id") Long userId) {
        return queryUserInfoService.execute(userId);
    }


}