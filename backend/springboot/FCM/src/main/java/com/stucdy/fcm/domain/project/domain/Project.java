package com.stucdy.fcm.domain.project.domain;

import com.stucdy.fcm.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String logoImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User projectManager;

    @OneToMany(mappedBy = "project")
    private List<ProjectUser> projectUsers;

    public void addProjectUsers(User user) {
        this.projectUsers.add(ProjectUser
                .builder()
                .project(this)
                .user(user)
                .build()
        );
    }

    @Builder
    public Project(String name, String description, String logoImage, User projectManager) {
        this.name = name;
        this.description = description;
        this.logoImage = logoImage;
        this.projectManager = projectManager;
    }

}