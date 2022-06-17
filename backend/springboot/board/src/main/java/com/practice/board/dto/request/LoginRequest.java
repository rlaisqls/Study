package com.practice.board.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LoginRequest {
    @NotNull(message = "아이디를 입력하세요.")
    private String username;

    @NotNull(message = "비밀번호를 입력하세요.")
    @Column(length = 30)
    private String password;
}