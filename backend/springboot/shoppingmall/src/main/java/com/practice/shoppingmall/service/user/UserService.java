package com.practice.shoppingmall.service.user;

import com.practice.shoppingmall.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.dto.response.user.FindUserResponse;

public interface UserService {

    FindUserResponse getUserInfo();

    void changePassword(PasswordChangeRequest request);
}