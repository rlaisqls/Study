package com.practice.board.facade;

import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.InvalidTokenException;
import com.practice.board.util.SecurityUtil;
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