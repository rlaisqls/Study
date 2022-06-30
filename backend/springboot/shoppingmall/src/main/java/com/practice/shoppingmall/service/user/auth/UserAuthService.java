package com.practice.shoppingmall.service.user.auth;

import com.practice.shoppingmall.dto.request.user.LoginUserRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;

public interface UserAuthService {

    TokenResponse login(LoginUserRequest request);

    TokenResponse reissue(String refreshToken);
}