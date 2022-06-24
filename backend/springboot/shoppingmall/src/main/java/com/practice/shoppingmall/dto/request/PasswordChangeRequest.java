package com.practice.shoppingmall.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PasswordChangeRequest {
    @NotBlank(message = "기존 비밀번호를 입력해주세요")
    private String oldPassword;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요")
    private String newPassword;
}