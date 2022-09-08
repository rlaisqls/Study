package com.stucdy.fcm.domain.project.service;

import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.repository.ProjectRepository;
import com.stucdy.fcm.domain.project.presentation.dto.request.CreateProjectRequest;
import com.stucdy.fcm.domain.project.presentation.dto.response.CreateProjectResponse;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateProjectService {

    private final ProjectRepository projectRepository;
    private final UserFacade userFacade;

    @Transactional
    public CreateProjectResponse execute(CreateProjectRequest request) {

        User user = userFacade.getCurrentUser();

        Project project = projectRepository.save(Project
                .builder()
                .name(request.getName())
                .projectManager(user)
                .build());

        return new CreateProjectResponse(project.getId());
    }

}