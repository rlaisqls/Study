package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.domain.Authority;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.UnVerifiedEmailException;
import com.practice.shoppingmall.domain.user.facade.UserAuthCodeFacade;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SignUpUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.SignUpUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserAuthCodeFacade authCodeFacade;
    private final UserFacade userFacade;

    @Transactional
    public SignUpUserResponse execute(SignUpUserRequest request) {

        String username = request.getUsername();
        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());
        String address = request.getAddress();

        userFacade.checkUserNameExists(username);

        if(!authCodeFacade.isVerified(email))
            throw UnVerifiedEmailException.EXCEPTION;

        User user = userRepository.save(User.builder()
                .username(username)
                .email(email)
                .password(password)
                .address(address)
                .authority(Authority.USER)
                .build());

        return new SignUpUserResponse(user.getId());
    }

}