package com.stucdy.fcm.domain.chat.domain.repository;

import com.stucdy.fcm.domain.chat.domain.Room;
import com.stucdy.fcm.domain.chat.domain.RoomUser;
import com.stucdy.fcm.domain.chat.domain.RoomUserId;
import com.stucdy.fcm.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserRepository extends JpaRepository<RoomUser, RoomUserId> {

    boolean existsByRoomAndUser(Room room, User user);
}