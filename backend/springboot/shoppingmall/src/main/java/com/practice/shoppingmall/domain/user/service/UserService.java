package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.presentation.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.FindUserResponse;

public interface UserService {

    FindUserResponse getUserInfo();

    void changePassword(PasswordChangeRequest request);
}