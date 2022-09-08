package com.stucdy.fcm.domain.chat.domain;

import com.stucdy.fcm.domain.chat.domain.enums.RoomType;
import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private RoomType roomType;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public String getRoomName(Chat chat) {

        if (roomType == RoomType.PROJECT) {
            return project.getName();
        } else {
            return chat.getUser().getName();
        }
    }

    public void addRoomUser(User user) {
        this.roomUsers.add(RoomUser
                .builder()
                .room(this)
                .user(user)
                .build());
    }
}