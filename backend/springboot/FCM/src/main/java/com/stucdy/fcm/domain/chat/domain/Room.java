package com.stucdy.fcm.domain.chat.domain;

import com.stucdy.fcm.domain.chat.domain.enums.RoomType;
import com.stucdy.fcm.domain.project.domain.Project;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomUser> roomUsers = new ArrayList<>();

    private RoomType roomType;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public String getRoomName() {

        if (roomType == RoomType.PROJECT) {
            return project.getName();
        }
        return "";
    }


}