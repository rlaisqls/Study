package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.LoginRequest;
import com.practice.shoppingmall.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.dto.request.RegisterRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.dto.response.UserInformationResponse;
import com.practice.shoppingmall.entity.user.Authority;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.item.ItemNotExistException;
import com.practice.shoppingmall.exception.user.UserAlreadyExistException;
import com.practice.shoppingmall.exception.user.UserNotFoundException;
import com.practice.shoppingmall.global.facade.UserFacade;
import com.practice.shoppingmall.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    public TokenResponse register(RegisterRequest request) {
        userRepository.findByUsernameOrEmail(request.getUsername(),request.getEmail())
                .ifPresent(user -> {throw UserAlreadyExistException.EXCEPTION;}); //null 아니면 실행

        User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .authority(Authority.ROLE_USER)
                .build());

        return jwtTokenProvider.createTokens(user.getId().toString());
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .filter(u -> passwordEncoder.matches(request.getPassword(),u.getPassword()))
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        return jwtTokenProvider.createTokens(user.getId().toString());
    }

    public UserInformationResponse getUserInfo() {
        return UserInformationResponse.from(userFacade.nowUser());
    }

    public void passwordChange(PasswordChangeRequest request) {
        userRepository.findByUsername(userFacade.nowUsername())
                .filter(u -> passwordEncoder.matches(request.getOldPassword(),u.getPassword()))
                .ifPresentOrElse(
                        user -> {
                            user.modifyPassword(request.getNewPassword());
                            userRepository.save(user);
                        }
                        ,()-> {throw UserNotFoundException.EXCEPTION;}
                );
    }
}