package com.practice.shoppingmall.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryUserInfoResponse {

    private Long userId;

    private String username;

    private String email;
}