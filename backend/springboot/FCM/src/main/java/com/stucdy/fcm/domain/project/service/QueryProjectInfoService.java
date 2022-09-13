package com.stucdy.fcm.domain.project.service;

import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.repository.ProjectRepository;
import com.stucdy.fcm.domain.project.facade.ProjectFacade;
import com.stucdy.fcm.domain.project.presentation.dto.response.QueryProjectInfoResponse;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryProjectInfoService {

    private final ProjectRepository projectRepository;
    private final ProjectFacade projectFacade;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryProjectInfoResponse execute(Long projectId) {

        Project project = projectFacade.getProjectById(projectId);

        return QueryProjectInfoResponse.of(project);
    }
}