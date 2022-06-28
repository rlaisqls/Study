package com.practice.shoppingmall.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordChangeRequest {
    @NotBlank(message = "기존 비밀번호를 입력해주세요")
    private String oldPassword;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요")
    private String newPassword;
}