package com.practice.shoppingmall.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse implements Response{
    private String accessToken;
    private String refreshToken;
}