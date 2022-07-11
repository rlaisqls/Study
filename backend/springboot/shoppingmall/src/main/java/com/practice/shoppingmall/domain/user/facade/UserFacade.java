package com.practice.shoppingmall.domain.user.facade;

import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.InvalidTokenException;
import com.practice.shoppingmall.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User nowUser() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }
}