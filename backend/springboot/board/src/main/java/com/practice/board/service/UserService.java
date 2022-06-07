package com.practice.board.service;

import com.practice.board.dto.request.LoginRequest;
import com.practice.board.dto.request.PasswordChangeRequest;
import com.practice.board.dto.response.UserInformationResponse;
import com.practice.board.entity.user.Authority;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.UserAlreadyExistException;
import com.practice.board.exception.UserNotFoundException;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(LoginRequest request) {
        if(userRepository.findByUsername(request.getUsername()).orElse(null) != null) {
            throw new UserAlreadyExistException();
        }
        userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.valueOf("ROLE_USER"))
                .activated(true)
                .build());
    }

    @Transactional(readOnly = true)
    public UserInformationResponse getUserInfo(String username) {
        return UserInformationResponse.from(userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public UserInformationResponse getUserInfo() {
        return UserInformationResponse.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public List<UserInformationResponse> getAllUserInfo() {
        return userRepository.findAll().stream()
                .map(UserInformationResponse::from)
                .collect(Collectors.toList());
    }

    public void passwordChange(PasswordChangeRequest request) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername)
                .orElseThrow(UserNotFoundException::new);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }
}