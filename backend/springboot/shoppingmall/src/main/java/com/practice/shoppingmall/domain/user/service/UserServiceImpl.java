package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.presentation.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.FindUserResponse;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.exception.BadUserInformationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Override
    public FindUserResponse getUserInfo() {

        User user = userFacade.nowUser();

        return FindUserResponse
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