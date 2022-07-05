package com.practice.shoppingmall.domain.user.service.auth;

import com.practice.shoppingmall.domain.user.presentation.dto.request.LoginUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.TokenResponse;

public interface UserAuthService {

    TokenResponse login(LoginUserRequest request);

    TokenResponse reissue(String refreshToken);
}