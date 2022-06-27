package com.practice.shoppingmall.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {
    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.getPrincipal().toString());
        return Optional.ofNullable(((UserDetails)authentication.getPrincipal()).getUsername());
    }
}