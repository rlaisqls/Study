package com.practice.shoppingmall.facade;

import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.InvalidTokenException;
import com.practice.shoppingmall.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User nowUser() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(()-> InvalidTokenException.EXCEPTION);
    }
}