package com.practice.shoppingmall.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindUserResponse {
    private String username;

    private String email;

    private String address;
}