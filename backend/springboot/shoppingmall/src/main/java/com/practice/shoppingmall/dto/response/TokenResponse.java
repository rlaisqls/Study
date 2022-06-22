package com.practice.shoppingmall.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}