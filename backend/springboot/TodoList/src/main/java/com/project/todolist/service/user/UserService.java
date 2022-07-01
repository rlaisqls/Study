package com.project.todolist.service.user;

import com.project.todolist.dto.request.RegisterRequest;
import com.project.todolist.dto.response.FindUserInfoResponse;
import com.project.todolist.entity.user.Authority;
import com.project.todolist.entity.user.User;
import com.project.todolist.entity.user.UserRepository;
import com.project.todolist.exception.UserAlreadyExistException;
import com.project.todolist.exception.UserNotFoundException;
import com.project.todolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    public void register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUserId()).isPresent())
            throw UserAlreadyExistException.EXCEPTION;

        userRepository.save(User
                .builder()
                .userId(request.getUserId())
                .name(request.getName())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.valueOf("ROLE_USER"))
                .build());
    }

    public FindUserInfoResponse findMyInfo() {

        User user = userFacade.currentUser();

        return FindUserInfoResponse.of(user);
    }

    public FindUserInfoResponse findUserInfo(String userId) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        return FindUserInfoResponse.of(user);
    }

}