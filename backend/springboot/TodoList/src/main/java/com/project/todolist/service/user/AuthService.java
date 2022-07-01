package com.project.todolist.service.user;

import com.project.todolist.dto.request.LoginRequest;
import com.project.todolist.dto.response.TokensResponse;
import com.project.todolist.entity.refeshToken.RefreshTokenRepository;
import com.project.todolist.entity.user.User;
import com.project.todolist.entity.user.UserRepository;
import com.project.todolist.exception.PasswordMisMatchException;
import com.project.todolist.security.jwt.JwtProperties;
import com.project.todolist.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokensResponse login(LoginRequest request) {

        User user = userRepository.findByUserId(request.getUserId())
                .filter(a -> passwordEncoder.matches(request.getPassword(), a.getPassword()))
                .orElseThrow(() -> PasswordMisMatchException.EXCEPTION);

        return jwtTokenProvider.createTokens(request.getUserId());
    }

    @Transactional
    public TokensResponse reissue(String refreshToken) {

        return jwtTokenProvider.reissue(refreshToken);
    }

}