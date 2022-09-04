package com.stucdy.fcm.domain.chat.domain.repository;

import com.stucdy.fcm.domain.chat.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}