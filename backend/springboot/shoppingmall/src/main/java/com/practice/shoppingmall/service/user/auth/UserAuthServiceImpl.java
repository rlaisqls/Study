package com.practice.shoppingmall.service.user.auth;

import com.practice.shoppingmall.dto.request.user.LoginUserRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.InvalidTokenException;
import com.practice.shoppingmall.exception.user.UserNotFoundException;
import com.practice.shoppingmall.security.jwt.JwtTokenProvider;
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
                .filter(u -> passwordEncoder.matches(request.getPassword(),u.getPassword()))
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }

    @Override
    @Transactional
    public TokenResponse reissue(String refreshToken) {

        if(!jwtTokenProvider.isRefreshToken(refreshToken)) throw InvalidTokenException.EXCEPTION;

        String uuid = jwtTokenProvider.getId(refreshToken);

        return new TokenResponse(jwtTokenProvider.createAccessToken(uuid), refreshToken);
    }
}