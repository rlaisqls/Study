package com.practice.shoppingmall.domain.user.presentation.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserRequest {

    @NotBlank(message = "username은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 15, message = "username은 15자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 30, message = "password는 8자 이상, 30자 이하여야 합니다.")
    private String password;
}