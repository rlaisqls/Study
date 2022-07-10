package com.practice.shoppingmall.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpUserRequest {
    @NotNull(message = "아이디를 입력해주세요")
    private String username;

    @NotNull(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식에 맞게 입력해주세요")
    private String email;

    @NotNull(message = "인증코드를 입력해주세요")
    @Size(min = 6, max = 6, message = "인증코드 형식이 올바르지 않습니다")
    private String authenticationCode;

    @NotNull(message = "비밀번호를 입력해주세요")
    private String password;

    @NotNull(message = "주소를 입력해주세요")
    private String address;
}