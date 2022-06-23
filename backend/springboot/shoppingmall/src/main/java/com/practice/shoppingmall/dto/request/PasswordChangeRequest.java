package com.practice.shoppingmall.dto.request;

import lombok.Getter;

@Getter
public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}