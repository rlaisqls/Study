package com.practice.shoppingmall.service.user.auth;

import com.practice.shoppingmall.dto.request.CheckIdDuplicationRequest;
import com.practice.shoppingmall.dto.request.EmailAuthenticationRequest;
import com.practice.shoppingmall.dto.request.user.SignUpUserRequest;
import com.practice.shoppingmall.dto.response.StringResponse;
import com.practice.shoppingmall.dto.response.user.SignUpUserResponse;
import org.springframework.data.jpa.repository.Modifying;


public interface UserSignUpService {
    SignUpUserResponse doSignUpUser(SignUpUserRequest request);

    StringResponse sendAuthenticationEmail(EmailAuthenticationRequest request);
}