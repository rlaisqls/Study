package com.stucdy.fcm.domain.project.domain.repository;

import com.stucdy.fcm.domain.project.domain.ProjectUser;
import com.stucdy.fcm.domain.project.domain.ProjectUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, ProjectUserId> {
}