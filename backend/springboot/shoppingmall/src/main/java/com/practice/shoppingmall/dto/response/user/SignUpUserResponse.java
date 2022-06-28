package com.practice.shoppingmall.dto.response.user;

import com.practice.shoppingmall.dto.response.Response;
import com.practice.shoppingmall.entity.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class SignUpUserResponse implements Response {

    private final UUID uuid;

    private final String username;

    private final String email;

    public static SignUpUserResponse of(User user) {
        return SignUpUserResponse.builder()
                .uuid(user.getUuid())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}