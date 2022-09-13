package com.stucdy.fcm.domain.user.service;

import com.stucdy.fcm.domain.auth.presentation.dto.TokenResponse;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.exception.PasswordMismatchException;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import com.stucdy.fcm.domain.user.presentation.dto.request.SignInRequest;
import com.stucdy.fcm.global.security.jwt.JwtProperties;
import com.stucdy.fcm.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SignInService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(SignInRequest request){

        String email = request.getEmail();
        String password = request.getPassword();

        User user = userFacade.getUserByEmail(email);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }

}