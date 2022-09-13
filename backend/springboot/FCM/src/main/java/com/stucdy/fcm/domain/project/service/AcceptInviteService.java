package com.stucdy.fcm.domain.project.service;

import com.stucdy.fcm.domain.project.domain.ProjectInvitation;
import com.stucdy.fcm.domain.project.domain.ProjectUserId;
import com.stucdy.fcm.domain.project.domain.repository.ProjectInvitationRepository;
import com.stucdy.fcm.domain.project.exception.UserNotInvitedException;
import com.stucdy.fcm.domain.project.presentation.dto.request.AcceptInviteRequest;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import com.stucdy.fcm.global.firebase.FCMFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AcceptInviteService {

    private final ProjectInvitationRepository projectInvitationRepository;
    private final FCMFacade fcmFacade;
    private final UserFacade userFacade;

    @Transactional
    public void execute(AcceptInviteRequest request) {

        User user =  userFacade.getCurrentUser();

        ProjectInvitation projectInvitation = projectInvitationRepository.findById(ProjectUserId
                        .builder()
                        .user(user.getId())
                        .project(request.getProjectId())
                        .build())
                .orElseThrow(() -> UserNotInvitedException.EXCEPTION);

        projectInvitation.getProject().addProjectUsers(user);
        projectInvitationRepository.delete(projectInvitation);
    }
}