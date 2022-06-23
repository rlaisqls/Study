package com.practice.shoppingmall.global.facade;

import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.InvalidTokenException;
import com.practice.shoppingmall.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public String nowUsername() {
        return SecurityUtil.getCurrentUsername()
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }

    public User nowUser() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }
}