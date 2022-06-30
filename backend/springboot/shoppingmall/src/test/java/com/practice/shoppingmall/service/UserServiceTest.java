package com.practice.shoppingmall.service;

import com.practice.shoppingmall.entity.user.User;
import com.practice.shoppingmall.entity.user.UserRepository;
import com.practice.shoppingmall.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Disabled
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    static private User user;

    @BeforeAll
    static void setUp(){
        user = User.builder().build();
    }

    @Test
    void 유저비밀번호_수정_성공() {
        //given
        String oldPassword = user.getPassword();
        String newPassword = "NewPassword";

        given(securityContextService.getPrincipal()).willReturn(new AuthenticationDetails(user));
        given(passwordEncoder.matches(oldPassword, user.getPassword())).willReturn(true);
        given(passwordEncoder.encode(newPassword)).willReturn(newPassword);

        //when
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .oldPassword(oldPassword)
                .newPassword(newPassword)
                .build();
        userService.modifyPassword(request);

        //then
        assertThat(user.getPassword()).isEqualTo(newPassword);
        verify(userRepository, times(1)).save(any());
    }
}