package com.practice.board.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokensResponse {
    private String accessToken;
    private String refreshToken;
}