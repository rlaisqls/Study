package com.project.dcb.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class RegisterRequest {
    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String gathering;
}