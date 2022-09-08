package com.stucdy.fcm.domain.chat.domain.repository;

import com.stucdy.fcm.domain.chat.domain.Room;
import com.stucdy.fcm.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndRoomUsers_user(Long id, User user);
}