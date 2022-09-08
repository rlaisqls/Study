package com.stucdy.fcm.domain.project.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateProjectRequest {

    @NotBlank(message = "name은 blank를 허용하지 않습니다.")
    private String name;

    @NotBlank(message = " projectLogoImage는 blank를 허용하지 않습니다.")
    private String projectLogoImage;

    @NotBlank(message = "description은 blank를 허용하지 않습니다.")
    private String description;
}