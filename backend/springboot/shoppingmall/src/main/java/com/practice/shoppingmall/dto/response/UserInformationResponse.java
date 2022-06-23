package com.practice.shoppingmall.dto.response;

import com.practice.shoppingmall.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;


@Builder
public class UserInformationResponse {
    private String username;

    private String email;

    private String password;

    private String address;

    public static UserInformationResponse from(User user) {
        return UserInformationResponse
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }
}