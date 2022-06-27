package com.practice.shoppingmall.service.user;

import com.practice.shoppingmall.dto.request.LoginRequest;
import com.practice.shoppingmall.dto.request.RegisterRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.entity.user.Authority;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.IncorrectTokenException;
import com.practice.shoppingmall.exception.user.UserAlreadyExistException;
import com.practice.shoppingmall.exception.user.UserNotFoundException;
import com.practice.shoppingmall.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public TokenResponse register(RegisterRequest request) {

        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {throw UserAlreadyExistException.EXCEPTION;}); //null 아니면 실행

        User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .authority(Authority.ROLE_USER)
                .build());

        return jwtTokenProvider.createTokens(user.getUuid().toString());
    }

    @Override
    @Transactional
    public TokenResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .filter(u -> passwordEncoder.matches(request.getPassword(),u.getPassword()))
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        return jwtTokenProvider.createTokens(user.getUuid().toString());
    }

    @Override
    @Transactional
    public TokenResponse reissue(String refreshToken) {

        if (!jwtTokenProvider.isRefreshToken(refreshToken)) throw IncorrectTokenException.EXCEPTION;

        String uuid = jwtTokenProvider.getId(refreshToken);

        return new TokenResponse(jwtTokenProvider.createAccessToken(uuid), refreshToken);
    }
}