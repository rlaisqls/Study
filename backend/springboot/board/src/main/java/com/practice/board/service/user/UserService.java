package com.practice.board.service.user;

import com.practice.board.dto.request.LoginRequest;
import com.practice.board.dto.request.PasswordChangeRequest;
import com.practice.board.dto.request.RegisterRequest;
import com.practice.board.dto.response.UserInformationResponse;
import com.practice.board.entity.user.Authority;
import com.practice.board.entity.user.GeneralUser;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.*;
import com.practice.board.facade.UserFacade;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    //가입
    public void register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw UserAlreadyExistException.EXCEPTION;

        userRepository.save(GeneralUser
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.valueOf("ROLE_USER"))
                .activated(true)
                .build());
    }

    //내 정보 조회
    public UserInformationResponse getUserInfo() {
        return new UserInformationResponse(SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(UserNotFoundException::new));
    }

    //전체 유저 정보 조회
    public List<UserInformationResponse> getAllUserInfo() {
        return userRepository.findAll()
                .stream()
                .map(UserInformationResponse::new)
                .collect(Collectors.toList());
    }

    //비밀번호 변경
    public void passwordChange(PasswordChangeRequest request) {

        GeneralUser user = (GeneralUser) userFacade.nowUser();

        if (user.getPassword() == null)
            throw new HandleAccessDeniedException();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        user.updateUser(request.getNewPassword());

        userRepository.save(user);

    }

    //유저정보 토큰에서 받기

}