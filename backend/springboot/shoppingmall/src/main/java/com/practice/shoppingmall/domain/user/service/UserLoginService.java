package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.PasswordMismatchException;
import com.practice.shoppingmall.domain.user.exception.UserNotFoundException;
import com.practice.shoppingmall.domain.user.presentation.dto.request.LoginUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.TokenResponse;
import com.practice.shoppingmall.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(LoginUserRequest request) {

        String accountId = request.getUsername();
        String password = request.getPassword();

        User user = userRepository.findByUsername(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        String accessToken = jwtTokenProvider.createAccessToken(request.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(request.getUsername());

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .refreshToken(refreshToken)
                .build();
    }

}