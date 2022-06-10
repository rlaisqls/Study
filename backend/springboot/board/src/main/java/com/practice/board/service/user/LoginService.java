package com.practice.board.service.user;

import com.practice.board.dto.response.TokensResponse;
import com.practice.board.entity.refeshToken.RefreshToken;
import com.practice.board.entity.refeshToken.RefreshTokenRepository;
import com.practice.board.entity.user.User;
import com.practice.board.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    @Value("${jwt.exp.refresh}")
    private Long refreshTokenValidTime;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public TokensResponse login(User user){
        RefreshToken refreshToken = refreshTokenRepository.save(
                RefreshToken.builder()
                        .uuid(String.valueOf(user.getUuid()))
                        .refreshToken(jwtTokenProvider.createRefreshToken(String.valueOf(user.getUuid())))
                        .expiration(refreshTokenValidTime)
                        .build());

        return TokensResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(String.valueOf(user.getUuid())))
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
}