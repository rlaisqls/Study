package com.project.todolist.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterRequest {
    @NotNull(message = "아이디를 입력하세요.")
    @Size(max = 10, message = "아이디는 10자 이하여야 합니다")
    @JsonProperty("user-id")
    private String userId;

    @NotNull(message = "이름을 입력하세요.")
    @Size(max = 10, message = "이름은 10자 이하여야 합니다")
    private String name;

    @NotNull(message = "나이를 입력하세요.")
    private Integer age;

    @NotNull(message = "비밀번호를 입력하세요.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자~15자여야 합니다")
    private String password;

}