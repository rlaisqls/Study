package com.project.dcb.service;

import com.project.dcb.Entity.user.Authority;
import com.project.dcb.Entity.Gathering;
import com.project.dcb.Entity.user.User;
import com.project.dcb.Entity.user.UserRepository;
import com.project.dcb.dto.request.LoginRequest;
import com.project.dcb.dto.request.RegisterRequest;
import com.project.dcb.dto.response.TokenResponse;
import com.project.dcb.dto.response.UserInfoResponse;
import com.project.dcb.exception.InvalidInformationException;
import com.project.dcb.exception.InvalidTokenException;
import com.project.dcb.exception.UserAlreadyExistException;
import com.project.dcb.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import com.project.dcb.util.SecurityUtil;
import org.apache.el.stream.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo() {
        return new UserInfoResponse(currentUser());
    }

    public void register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).orElse(null) != null)
            throw new UserAlreadyExistException();
        userRepository.save(new User(request, passwordEncoder));
    }

    public void authorization(String username, String authority) {
        userRepository.findByUsername(username).ifPresent(user -> {
            user.setAuthority(Authority.valueOf(authority));
            userRepository.save(user);
        });
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {
        return new TokenResponse(jwtTokenProvider.createToken(request.getUsername()));
    }

    public User currentUser() {
        return userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .orElseThrow(InvalidTokenException::new);
    }
}