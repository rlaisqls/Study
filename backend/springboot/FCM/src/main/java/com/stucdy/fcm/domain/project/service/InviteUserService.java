package com.stucdy.fcm.domain.project.service;

import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.ProjectInvitation;
import com.stucdy.fcm.domain.project.domain.repository.ProjectInvitationRepository;
import com.stucdy.fcm.domain.project.facade.ProjectFacade;
import com.stucdy.fcm.domain.project.presentation.dto.request.InviteUserRequest;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import com.stucdy.fcm.global.firebase.FCMFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InviteUserService {

    private final ProjectInvitationRepository projectInvitationRepository;
    private final ProjectFacade projectFacade;
    private final FCMFacade fcmFacade;
    private final UserFacade userFacade;

    @Transactional
    public void execute(InviteUserRequest request) {

        User user = userFacade.getCurrentUser();
        Long projectId = request.getProjectId();

        Project project = projectFacade.getProjectById(projectId);
        projectFacade.checkProjectManager(project, user);

        User userToInvite = userFacade.getUserById(request.getUserId());

        ProjectInvitation projectInvitation = projectInvitationRepository.save(ProjectInvitation
                .builder()
                .user(userToInvite)
                .project(project)
                .build());

        fcmFacade.sendNotification(userToInvite, projectInvitation);
    }
}