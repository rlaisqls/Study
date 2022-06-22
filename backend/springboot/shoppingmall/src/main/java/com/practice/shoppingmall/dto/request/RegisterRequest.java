package com.practice.shoppingmall.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class RegisterRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String address;
}