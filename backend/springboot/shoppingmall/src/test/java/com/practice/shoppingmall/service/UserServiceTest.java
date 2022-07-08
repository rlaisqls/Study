package com.practice.shoppingmall.service;

import com.practice.shoppingmall.constant.UserConstant;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.AuthenticationCodeRepository;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.service.auth.UserSignUpServiceImpl;
import com.practice.shoppingmall.domain.user.service.mail.MailService;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {
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

    static private User user;

    @BeforeAll
    static void setUp() {
        user =
    }


}