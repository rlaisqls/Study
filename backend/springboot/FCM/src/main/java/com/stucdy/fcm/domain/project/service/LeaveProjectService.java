package com.stucdy.fcm.domain.project.service;

import com.stucdy.fcm.domain.project.domain.ProjectUserId;
import com.stucdy.fcm.domain.project.domain.repository.ProjectUserRepository;
import com.stucdy.fcm.domain.project.presentation.dto.request.LeaveProjectRequest;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LeaveProjectService {

    private final UserFacade userFacade;
    private final ProjectUserRepository projectUserRepository;

    @Transactional
    public void execute(LeaveProjectRequest request) {

        User user = userFacade.getCurrentUser();

        projectUserRepository.deleteById(ProjectUserId
                .builder()
                .user(user.getId())
                .project(request.getProjectId())
                .build());
    }
}