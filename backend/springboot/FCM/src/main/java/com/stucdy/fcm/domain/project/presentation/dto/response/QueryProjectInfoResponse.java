package com.stucdy.fcm.domain.project.presentation.dto.response;

import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.ProjectUser;
import com.stucdy.fcm.domain.user.presentation.dto.response.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class QueryProjectInfoResponse {

    private String name;

    private String description;

    private String projectLogoImage;

    private UserResponse projectManager;

    private List<UserResponse> projectUsers;

    public static QueryProjectInfoResponse of(Project project) {

        return QueryProjectInfoResponse
                .builder()
                .name(project.getName())
                .description(project.getDescription())
                .projectLogoImage(project.getProjectLogoImage())
                .projectManager(UserResponse.of(project.getProjectManager()))
                .projectUsers(project.getProjectUsers()
                        .stream()
                        .map(ProjectUser::getUser)
                        .map(UserResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }
}