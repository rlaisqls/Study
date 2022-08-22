package com.study.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final SocketIOServer socketIOServer;

    @OnEvent("hello")
    public void hello(SocketIOClient socketIOClient, @RequestBody HelloDto request){
        System.out.println("SocketController.hello");
        System.out.println("socketIOServer = " + socketIOServer);
        System.out.println("socketIOClient = " + socketIOClient);
        System.out.println("request.getMessage() = " + request.getMessage());
    }

}