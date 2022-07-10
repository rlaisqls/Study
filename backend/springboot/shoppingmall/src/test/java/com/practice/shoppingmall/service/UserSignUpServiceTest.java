package com.practice.shoppingmall.service;

import com.practice.shoppingmall.constant.UserConstant;
import com.practice.shoppingmall.domain.user.domain.AuthenticationCode;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.AuthenticationCodeRepository;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.UserAlreadyExistException;
import com.practice.shoppingmall.domain.user.presentation.dto.request.EmailAuthenticationRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.request.SignUpUserRequest;
import com.practice.shoppingmall.domain.user.presentation.dto.response.SignUpUserResponse;
import com.practice.shoppingmall.domain.user.service.auth.UserSignUpServiceImpl;
import com.practice.shoppingmall.domain.user.service.mail.MailService;
import com.practice.shoppingmall.util.UserBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserSignUpServiceTest {
    @InjectMocks
    private UserSignUpServiceImpl userSignUpService;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationCodeRepository authenticationCodeRepository;
    @Mock
    private MailService mailService;

    private static User user;

    @BeforeAll
    static void setUp() {
        user = UserBuilder.build();
    }

    @Test
    void 회원가입_성공(){
        //given
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        String address = user.getAddress();
        String authenticationCode = UserConstant.AUTHENTICATION_CODE;
        AuthenticationCode code = new AuthenticationCode(email, authenticationCode);

        given(userRepository.findByUsername(username)).willReturn(Optional.empty());
        given(authenticationCodeRepository.findById(email)).willReturn(Optional.of(code));
        given(userRepository.save(any())).willReturn(UserBuilder.build());
        given(passwordEncoder.encode(password)).willReturn(password);

        //when
        SignUpUserRequest request = SignUpUserRequest.builder()
                .username(username)
                .email(email)
                .authenticationCode(authenticationCode)
                .password(password)
                .address(address)
                .build();
        SignUpUserResponse response = userSignUpService.doSignUpUser(request);

        //then
        assertThat(response.getId()).isNotNull();
    }

    @Test
    void 이메일_전송_성공() {
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        try {
            EmailAuthenticationRequest request = EmailAuthenticationRequest
                    .builder()
                    .email(user.getEmail())
                    .build();
            userSignUpService.sendAuthenticationEmail(request);
        } catch (Exception e){
            fail("이메일 전송 실패");
        }
    }

    @Test
    void 이메일_중복_실패() {
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
        try {
            EmailAuthenticationRequest request = EmailAuthenticationRequest
                    .builder()
                    .email(user.getEmail())
                    .build();
            userSignUpService.sendAuthenticationEmail(request);
            fail("중복유저 가입됨");
        } catch (Exception e){
            assertThat(e).isInstanceOf(UserAlreadyExistException.class);
        }
    }

    @Test
    void 이메일_전송_실패() {
        //given
        given(mailService.sendEmail(any())).willThrow(new RuntimeException());
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());

        //when
        Exception exception = null;
        try {
            EmailAuthenticationRequest request = EmailAuthenticationRequest
                    .builder()
                    .email(user.getEmail())
                    .build();
            userSignUpService.sendAuthenticationEmail(request);
        } catch (RuntimeException e){
            exception = e;
        }

        //then
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}