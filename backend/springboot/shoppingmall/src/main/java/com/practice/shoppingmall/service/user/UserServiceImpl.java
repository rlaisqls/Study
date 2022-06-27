package com.practice.shoppingmall.service.user;

import com.practice.shoppingmall.dto.request.LoginRequest;
import com.practice.shoppingmall.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.dto.request.RegisterRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.dto.response.UserInformationResponse;
import com.practice.shoppingmall.entity.user.Authority;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.user.BadUserInformationException;
import com.practice.shoppingmall.exception.user.UserAlreadyExistException;
import com.practice.shoppingmall.exception.user.UserNotFoundException;
import com.practice.shoppingmall.facade.UserFacade;
import com.practice.shoppingmall.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    @Override
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

        return jwtTokenProvider.createTokens(user.getId().toString());
    }

    @Override
    public TokenResponse login(LoginRequest request) {

        User user = verifyUser(request);
        return jwtTokenProvider.createTokens(user.getId().toString());
    }

    private User verifyUser(LoginRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(u -> passwordEncoder.matches(request.getPassword(),u.getPassword()))
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }

    @Override
    public UserInformationResponse getUserInfo() {

        User user = userFacade.nowUser();
        return UserInformationResponse
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }

    @Override
    public void changePassword(PasswordChangeRequest request) {

        User user = userFacade.nowUser();

        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            throw BadUserInformationException.EXCEPTION;

        user.changePassword(request.getNewPassword());
    }
}