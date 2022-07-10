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
public class PasswordChangeRequest {
    @NotNull(message = "기존 비밀번호를 입력해주세요")
    private String oldPassword;

    @NotNull(message = "새로운 비밀번호를 입력해주세요")
    private String newPassword;
}