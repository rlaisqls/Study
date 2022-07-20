package com.practice.shoppingmall.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class PasswordChangeRequest {
    @NotBlank(message = "old_password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String oldPassword;

    @NotBlank(message = "new_password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" + "<=>?@＼^_`{|}~]{8,30}$", message = "password는 숫자, 특수문자가 포함되어야 합니다.")
    private String newPassword;
}