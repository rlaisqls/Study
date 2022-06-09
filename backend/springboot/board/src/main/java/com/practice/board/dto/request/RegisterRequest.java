package com.practice.board.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    @Column(length = 30, unique = true)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Column(length = 30)
    private String password;
}