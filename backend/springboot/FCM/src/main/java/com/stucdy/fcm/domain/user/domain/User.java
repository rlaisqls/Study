package com.stucdy.fcm.domain.user.domain;

import com.stucdy.fcm.domain.chat.domain.RoomUser;
import com.stucdy.fcm.domain.project.domain.ProjectUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Id
    private long id;

    private String name;

    private String email;

    private String password;

    private String profileImage;

    private String deviceToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<ProjectUser> projectUsers;

    @Builder
    public User(String name, String email, String password, String deviceToken, String profileImage) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.deviceToken = deviceToken;
        this.profileImage = profileImage;
    }
}