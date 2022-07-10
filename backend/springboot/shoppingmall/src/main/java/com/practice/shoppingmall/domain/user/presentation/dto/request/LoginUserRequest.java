package com.practice.shoppingmall.domain.user.presentation.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserRequest {
    @NotNull(message = "아이디를 입력해주세요")
    private String username;

    @NotNull(message = "비밀번호를 입력해주세요")
    private String password;
}