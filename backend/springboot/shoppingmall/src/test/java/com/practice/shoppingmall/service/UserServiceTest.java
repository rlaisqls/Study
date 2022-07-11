package com.practice.shoppingmall.service;


import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.domain.repository.UserRepository;
import com.practice.shoppingmall.domain.user.exception.BadUserInformationException;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import com.practice.shoppingmall.domain.user.presentation.dto.request.PasswordChangeRequest;
import com.practice.shoppingmall.domain.user.service.UserServiceImpl;
import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.util.UserBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserFacade userFacade;
    @Mock
    private UserRepository userRepository;

    private static User user;

    @BeforeAll
    static void setUp() {
        user = UserBuilder.build();
    }

    @Test
    void 유저비밀번호_수정_성공() {
        //given
        String oldPassword = user.getPassword();
        String newPassword = "NewPassword";

        given(userFacade.nowUser()).willReturn(user);
        given(passwordEncoder.matches(oldPassword, user.getPassword())).willReturn(true);
        given(passwordEncoder.encode(newPassword)).willReturn(newPassword);

        //when
        PasswordChangeRequest request = PasswordChangeRequest.builder()
                .oldPassword(oldPassword)
                .newPassword(newPassword)
                .build();
        userService.changePassword(request);

        //then
        assertThat(user.getPassword()).isEqualTo(newPassword);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void 유저비밀번호_수정_실패() {
        //given
        String oldPassword = user.getPassword();
        String newPassword = "NewPassword";

        given(userFacade.nowUser()).willReturn(user);
        given(passwordEncoder.matches(oldPassword, user.getPassword())).willReturn(false);

        //when
        try {
            PasswordChangeRequest request = PasswordChangeRequest.builder()
                    .oldPassword(oldPassword)
                    .newPassword(newPassword)
                    .build();
            userService.changePassword(request);
            fail("No error");
        } catch (BusinessException e) {
            //then
            assertThat(e).isInstanceOf(BadUserInformationException.class);
            verify(userRepository, times(0)).save(any());
        }
    }

}