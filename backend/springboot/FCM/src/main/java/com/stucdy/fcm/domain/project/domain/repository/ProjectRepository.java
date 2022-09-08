package com.stucdy.fcm.domain.project.domain.repository;

import com.stucdy.fcm.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}