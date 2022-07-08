package com.practice.shoppingmall.domain.user.service.auth;

import com.practice.shoppingmall.domain.user.presentation.dto.request.LoginUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.TokenResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.InvalidTokenException;
import com.practice.shoppingmall.domain.user.exception.UserNotFoundException;
import com.practice.shoppingmall.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public TokenResponse login(LoginUserRequest request) {

        User user = verifyUser(request);
        return jwtTokenProvider.createTokens(user.getId().toString());
    }

    private User verifyUser(LoginUserRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPassword()))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    @Transactional
    public TokenResponse reissue(String refreshToken) {

        if (!jwtTokenProvider.isRefreshToken(refreshToken)) throw InvalidTokenException.EXCEPTION;

        String uuid = jwtTokenProvider.getId(refreshToken);

        return new TokenResponse(jwtTokenProvider.createAccessToken(uuid), refreshToken);
    }
}