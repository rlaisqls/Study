package com.practice.shoppingmall.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class VerifyAuthCodeRequest {

    @NotBlank(message = "email은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "email 형식이 올바르지 않습니다")
    private String email;

    @Size(min = 6, max = 6, message = "인증코드 형식이 올바르지 않습니다")
    private String code;
}