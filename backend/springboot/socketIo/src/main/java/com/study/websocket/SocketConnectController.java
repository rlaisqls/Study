package com.study.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SocketConnectController {

    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("SocketConnectController.onConnect");
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("SocketConnectController.onDisconnect");
    }

}