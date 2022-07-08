package com.practice.shoppingmall.domain.user.service.auth;

import com.practice.shoppingmall.domain.user.presentation.dto.request.SendMailRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SignUpUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.SignUpUserResponse;
import com.practice.shoppingmall.domain.user.domain.AuthenticationCode;
import com.practice.shoppingmall.domain.user.domain.repository.AuthenticationCodeRepository;
import com.practice.shoppingmall.domain.user.domain.Authority;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.presentation.dto.request.EmailAuthenticationRequest;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.BadAuthenticationCodeException;
import com.practice.shoppingmall.domain.user.exception.UserAlreadyExistException;
import com.practice.shoppingmall.domain.user.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserSignUpServiceImpl implements UserSignUpService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationCodeRepository authenticationCodeRepository;
    private final MailService mailService;

    public static final Random RANDOM = new Random();

    @Override
    public SignUpUserResponse doSignUpUser(SignUpUserRequest request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent())
            throw UserAlreadyExistException.EXCEPTION;

        authenticationCodeRepository.findById(request.getEmail())
                .filter(a -> request.getAuthenticationCode().equals(a.getCode()))
                .orElseThrow(() -> BadAuthenticationCodeException.EXCEPTION);

        User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .authority(Authority.ROLE_USER)
                .build());

        return new SignUpUserResponse(user.getId());
    }

    @Override
    public void sendAuthenticationEmail(EmailAuthenticationRequest request) {

        String code = createAuthenticationCode();
        String emailAddress = request.getEmail();

        if(userRepository.findByEmail(emailAddress).isPresent())
                throw UserAlreadyExistException.EXCEPTION; //null이 아니면 실행

        AuthenticationCode authenticationCode = new AuthenticationCode(emailAddress, code);

        authenticationCodeRepository.save(authenticationCode);

        mailService.sendEmail(SendMailRequest.builder()
                .address(emailAddress)
                .authenticationCode(code)
                .title("이메일 인증")
                .build()
        );
    }

    private String createAuthenticationCode() {
        return String.format("%06d", RANDOM.nextInt(1000000) % 1000000);
    }
}