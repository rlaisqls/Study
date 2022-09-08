package com.stucdy.fcm.domain.project.service;

import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.ProjectUser;
import com.stucdy.fcm.domain.project.facade.ProjectFacade;
import com.stucdy.fcm.domain.project.presentation.dto.request.ParticipateProjectRequest;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ParticipateProjectService {

    private final ProjectFacade projectFacade;
    private final UserFacade userFacade;

    @Transactional
    public void execute(ParticipateProjectRequest request) {

        User user = userFacade.getCurrentUser();

        Project project = projectFacade.getProjectById(request.getProjectId());

        project.addProjectUsers(ProjectUser
                .builder()
                .project(project)
                .user(user)
                .build());
    }
}