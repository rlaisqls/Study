package com.practice.shoppingmall.service.user;

import com.practice.shoppingmall.dto.request.LoginRequest;
import com.practice.shoppingmall.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.dto.request.RegisterRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.dto.response.FindUserResponse;

public interface UserService {
    TokenResponse register(RegisterRequest request);

    TokenResponse login(LoginRequest request);

    FindUserResponse getUserInfo();

    void changePassword(PasswordChangeRequest request);
}