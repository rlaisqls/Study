package com.practice.shoppingmall.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;
    private ZonedDateTime expiredAt;
    private String refreshToken;
}