package com.practice.board.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {
    @NotNull(message = "아이디를 입력하세요.")
    @Size(min = 4, max = 20, message = "아이디는 6-20자여야합니다.")
    private String username;

    @NotNull(message = "이메일을 입력하세요.")
    @Email
    private String email;

    @NotNull(message = "비밀번호를 입력하세요.")
    @Pattern(regexp="(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 특수기호 숫자 포함 8-20자여야 합니다")
    private String password;
}