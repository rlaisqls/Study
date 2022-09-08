package com.stucdy.fcm.domain.project.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ParticipateProjectRequest {
    @NotNull(message = "projectId는 null이어선 안됩니다.")
    private Long projectId;
}