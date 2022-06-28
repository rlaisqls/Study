package com.practice.shoppingmall.service.user;

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
import com.practice.shoppingmall.service.user.mail.MailService;
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

        userRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail())
                .orElseThrow(() -> UserAlreadyExistException.EXCEPTION);

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

        return SignUpUserResponse.of(user);
    }

    @Override
    public StringResponse sendAuthenticationEmail(EmailAuthenticationRequest request) {

        String authenticationCode = createAuthenticationCode();
        String emailAddress = request.getEmail();

        userRepository.findByEmail(emailAddress)
                .orElseThrow(() -> UserAlreadyExistException.EXCEPTION); //null이 아니면 실행

        authenticationCodeRepository.save(new AuthenticationCode(emailAddress, authenticationCode));

        mailService.sendEmail(SendMailRequest.builder()
                .address(emailAddress)
                .authenticationCode(authenticationCode)
                .title("이메일 인증")
                .build()
        );

        return new StringResponse("메일을 전송했습니다");
    }

    private String createAuthenticationCode() {
        return String.format("%06d", RANDOM.nextInt(1000000) % 1000000);
    }
}