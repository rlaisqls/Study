package com.practice.shoppingmall.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordChangeRequest {
    @NotBlank(message = "old_password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String oldPassword;

    @NotBlank(message = "new_password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]$", message = "new_password는 영문 대소문자와 숫자, 특수기호를 포함해야 합니다")
    @Length(min = 1, max = 30, message = "new_password는 8자 이상, 30자 이하여야 합니다.")
    private String newPassword;
}