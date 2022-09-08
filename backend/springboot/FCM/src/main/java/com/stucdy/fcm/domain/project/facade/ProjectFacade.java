package com.stucdy.fcm.domain.project.facade;

import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectFacade {

    private final ProjectRepository projectRepository;

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow();
    }
}