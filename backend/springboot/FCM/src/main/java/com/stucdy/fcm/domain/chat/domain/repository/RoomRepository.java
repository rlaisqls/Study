package com.stucdy.fcm.domain.chat.domain.repository;

import com.stucdy.fcm.domain.chat.domain.Room;
import com.stucdy.fcm.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
    Optional<Room> findByIdAndRoomUsers_user(Long id, User user);
}