package com.practice.shoppingmall.domain.user.service.auth;

import com.practice.shoppingmall.domain.user.presentation.dto.request.SignUpUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.EmailAuthenticationRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.SignUpUserResponse;


public interface UserSignUpService {
    SignUpUserResponse doSignUpUser(SignUpUserRequest request);

    StringResponse sendAuthenticationEmail(EmailAuthenticationRequest request);
}