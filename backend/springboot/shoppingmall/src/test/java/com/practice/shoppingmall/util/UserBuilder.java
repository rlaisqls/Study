package com.practice.shoppingmall.util;

import com.practice.shoppingmall.constant.UserConstant;
import com.practice.shoppingmall.domain.user.domain.User;

public class UserBuilder {

    public static User build() {
        return User
                .builder()
                .id(1L)
                .username(UserConstant.USERNAME)
                .email(UserConstant.EMAIL)
                .password(UserConstant.PASSWORD)
                .address(UserConstant.ADDRESS)
                .build();
    }
}