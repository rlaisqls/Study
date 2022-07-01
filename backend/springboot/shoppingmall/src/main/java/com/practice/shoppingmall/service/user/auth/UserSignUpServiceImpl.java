package com.practice.shoppingmall.service.user.auth;

import com.practice.shoppingmall.dto.request.EmailAuthenticationRequest;
import com.practice.shoppingmall.dto.request.SendMailRequest;
import com.practice.shoppingmall.dto.request.user.SignUpUserRequest;
import com.practice.shoppingmall.dto.response.StringResponse;
import com.practice.shoppingmall.dto.response.user.SignUpUserResponse;
import com.practice.shoppingmall.entity.redis.authenticationcode.AuthenticationCode;
import com.practice.shoppingmall.entity.redis.authenticationcode.AuthenticationCodeRepository;
import com.practice.shoppingmall.entity.user.Authority;
import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.exception.BadAuthenticationCodeException;
import com.practice.shoppingmall.exception.user.UserAlreadyExistException;
import com.practice.shoppingmall.service.mail.MailService;
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
    public StringResponse sendAuthenticationEmail(EmailAuthenticationRequest request) {

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

        return new StringResponse("메일을 전송했습니다");
    }

    private String createAuthenticationCode() {
        return String.format("%06d", RANDOM.nextInt(1000000) % 1000000);
    }
}