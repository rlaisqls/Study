package com.practice.shoppingmall.domain.user.service;

import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.PasswordMismatchException;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import com.practice.shoppingmall.domain.user.presentation.dto.request.PasswordChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangePasswordService {

    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public void execute(PasswordChangeRequest request) {

        User user = userFacade.getCurrentUser();

        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        user.changePassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}