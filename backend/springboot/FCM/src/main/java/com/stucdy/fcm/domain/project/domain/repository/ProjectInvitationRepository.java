package com.stucdy.fcm.domain.project.domain.repository;

import com.stucdy.fcm.domain.project.domain.ProjectInvitation;
import com.stucdy.fcm.domain.project.domain.ProjectUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInvitationRepository extends JpaRepository<ProjectInvitation, ProjectUserId> {
}