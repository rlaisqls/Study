package com.stucdy.fcm.domain.chat.presentation;


import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.stucdy.fcm.domain.chat.presentation.dto.request.SendChatRequest;
import com.stucdy.fcm.domain.chat.service.SendChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class ChatSocketController {

    private final SocketIOServer socketIOServer;
    private final SendChatService sendChatService;

    @OnEvent("chat")
    public void sendChat(SocketIOClient socketIOClient, @RequestBody @Valid SendChatRequest request){
        sendChatService.execute(socketIOServer, socketIOClient, request);
    }

}