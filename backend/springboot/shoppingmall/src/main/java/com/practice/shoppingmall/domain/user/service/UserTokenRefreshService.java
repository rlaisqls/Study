package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.auth.domain.RefreshToken;
import com.practice.shoppingmall.domain.auth.domain.repository.RefreshTokenRepository;
import com.practice.shoppingmall.domain.auth.exception.RefreshTokenNotFoundException;
import com.practice.shoppingmall.domain.user.presentation.dto.response.TokenResponse;
import com.practice.shoppingmall.global.security.jwt.JwtProperties;
import com.practice.shoppingmall.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserTokenRefreshService {

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtProperties jwtProperties;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse execute(String refreshToken) {

        RefreshToken redisRefreshToken = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String username = redisRefreshToken.getUsername();
        String newRefreshToken = jwtTokenProvider.createRefreshToken(username);

        redisRefreshToken.updateToken(refreshToken, jwtProperties.getRefreshExp());

        String newAccessToken = jwtTokenProvider.createAccessToken(username);

        return TokenResponse
                .builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}