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
        System.out.println(SecurityUtil.getCurrentUsername());
        User user = SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(InvalidTokenException::new);
        return new UserInfoResponse(user);
    }

    public void register(RegisterRequest request) {
        if(userRepository.findByUsername(request.getUsername()).orElse(null) != null) {
            throw new UserAlreadyExistException();
        }
        userRepository.save(User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .gathering(Gathering.valueOf("CLASS_"+request.getGathering().charAt(0)+"_"+request.getGathering().charAt(2)))
                .authority(Authority.valueOf("ROLE_USER"))
                .build());
    }

    public void authorization(String username,String authority) {
        userRepository.findByUsername(username).ifPresent(user->{
            user.setAuthority(Authority.valueOf(authority));
            userRepository.save(user);
        });
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .filter(a -> passwordEncoder.matches(request.getPassword(),a.getPassword()))
                .orElseThrow(InvalidInformationException::new);
        return new TokenResponse(jwtTokenProvider.createToken((user.getUsername())));
    }
}