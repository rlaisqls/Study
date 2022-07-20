package com.practice.shoppingmall.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpUserRequest {
    @NotBlank(message = "username은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 15, message = "username은 15자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "email은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "email 형식이 올바르지 않습니다")
    private String email;

    @NotBlank(message = "password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" + "<=>?@＼^_`{|}~]{8,30}$", message = "password는 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "address는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 60, message = "address는 60자 이하여야 합니다.")
    private String address;
}