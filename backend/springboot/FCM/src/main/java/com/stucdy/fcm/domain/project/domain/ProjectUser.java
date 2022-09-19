package com.stucdy.fcm.domain.project.domain;

import com.stucdy.fcm.domain.project.domain.enums.ProjectUserType;
import com.stucdy.fcm.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ProjectUserId.class)
@Entity
public class ProjectUser {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private ProjectUserType projectUserType;

    @Builder
    public ProjectUser(Project project, User user) {
        this.project = project;
        this.user = user;
    }

}