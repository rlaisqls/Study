package com.practice.shoppingmall.service.user;

import com.practice.shoppingmall.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.dto.response.user.FindUserResponse;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.user.BadUserInformationException;
import com.practice.shoppingmall.facade.UserFacade;
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