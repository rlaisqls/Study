package com.stucdy.fcm.domain.project.facade;

import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.repository.ProjectRepository;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.exception.ForbiddenException;
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

    public void checkProjectManager(Project project, User user) {
        if(project.getProjectManager() != user) {
            throw ForbiddenException.EXCEPTION;
        }
    }
}