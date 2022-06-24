package com.practice.shoppingmall.security.auth;


import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(uuid))
                .orElseThrow(()->UserNotFoundException.EXCEPTION);
    }

}