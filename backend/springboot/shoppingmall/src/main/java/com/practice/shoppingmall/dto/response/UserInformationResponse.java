package com.practice.shoppingmall.dto.response;

import com.practice.shoppingmall.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserInformationResponse {
    private String username;

    private String email;

    private String address;
}