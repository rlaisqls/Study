package com.stucdy.fcm.domain.chat.facade;

import com.stucdy.fcm.domain.chat.domain.Room;
import com.stucdy.fcm.domain.chat.domain.repository.RoomUserRepository;
import com.stucdy.fcm.domain.chat.exception.RoomUserNotFoundException;
import com.stucdy.fcm.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoomUserFacade {

    private final RoomUserRepository roomUserRepository;

    public void checkRoomUserExist(Room room, User user){
        if(!roomUserRepository.existsByRoomAndUser(room, user)) {
            throw RoomUserNotFoundException.EXCEPTION;
        }
    }
}