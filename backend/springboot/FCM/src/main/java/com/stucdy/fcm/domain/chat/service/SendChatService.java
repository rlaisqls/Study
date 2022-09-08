package com.stucdy.fcm.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.stucdy.fcm.domain.chat.domain.Chat;
import com.stucdy.fcm.domain.chat.domain.Room;
import com.stucdy.fcm.domain.chat.domain.repository.ChatRepository;
import com.stucdy.fcm.domain.chat.facade.RoomFacade;
import com.stucdy.fcm.domain.chat.facade.RoomUserFacade;
import com.stucdy.fcm.domain.chat.presentation.dto.request.SendChatRequest;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import com.stucdy.fcm.global.firebase.FCMFacade;
import com.stucdy.fcm.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SendChatService {

    private final ChatRepository chatRepository;
    private final UserFacade userFacade;
    private final RoomFacade roomFacade;
    private final RoomUserFacade roomUserFacade;
    private final FCMFacade fcmFacade;

    @Transactional
    public void execute(SocketIOServer socketIOServer, SocketIOClient socketIOClient, SendChatRequest request) {

        User user = userFacade.getCurrentUser(socketIOClient);
        Room room = roomFacade.getRoomById(request.getRoomId());

        roomUserFacade.checkRoomUserExist(room, user);

        Chat chat = chatRepository.save(Chat
                .builder()
                .message(request.getMessage())
                .room(room)
                .user(user)
                .build());

        fcmFacade.sendNotification(user, chat);

        socketIOServer.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.CHAT, chat);
    }
}