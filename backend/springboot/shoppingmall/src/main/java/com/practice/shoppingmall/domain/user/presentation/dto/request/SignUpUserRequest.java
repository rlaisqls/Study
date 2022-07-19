package com.practice.shoppingmall.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpUserRequest {
    @NotBlank(message = "username은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 15, message = "username은 15자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "email은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "email 형식이 올바르지 않습니다")
    private String email;

    @Size(min = 6, max = 6, message = "인증코드 형식이 올바르지 않습니다")
    private String authenticationCode;

    @NotBlank(message = "password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]$", message = "password는 영문 대소문자와 숫자, 특수기호를 포함해야 합니다")
    @Length(min = 8, max = 30, message = "password는 8자 이상, 30자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "address는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 60, message = "address는 60자 이하여야 합니다.")
    private String address;
}