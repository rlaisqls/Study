package com.stucdy.fcm.domain.user.service;

import com.stucdy.fcm.domain.auth.presentation.dto.TokenResponse;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.domain.repository.UserRepository;
import com.stucdy.fcm.domain.user.exception.UserAlreadyExistException;
import com.stucdy.fcm.domain.user.presentation.dto.request.SignUpRequest;
import com.stucdy.fcm.global.security.jwt.JwtProperties;
import com.stucdy.fcm.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(SignUpRequest request) {

        String email = request.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .email(email)
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .deviceToken(request.getDeviceToken())
                .build());

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