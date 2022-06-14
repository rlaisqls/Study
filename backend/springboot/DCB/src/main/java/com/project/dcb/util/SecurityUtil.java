package com.project.dcb.util;

import com.project.dcb.exception.InvalidTokenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null|| ((UserDetails) authentication.getPrincipal()).getUsername() == null)
            throw new InvalidTokenException();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

}