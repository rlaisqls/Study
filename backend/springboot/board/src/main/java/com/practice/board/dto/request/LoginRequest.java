package com.practice.board.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    @Column(length = 30, unique = true)
    private String username;

    @NotNull
    @Column(length = 30)
    private String password;
}