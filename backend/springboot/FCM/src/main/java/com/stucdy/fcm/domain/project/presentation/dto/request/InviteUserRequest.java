package com.stucdy.fcm.domain.project.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class InviteUserRequest {

    @NotBlank(message = "project_id은 blank를 허용하지 않습니다.")
    private Long projectId;

    @NotBlank(message = "user_id은 blank를 허용하지 않습니다.")
    private Long userId;

}