package com.study.jwtlogin.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokensDto {
    private String accessToken;
    private String refreshToken;
}