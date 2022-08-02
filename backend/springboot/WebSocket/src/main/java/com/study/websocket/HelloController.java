package com.study.websocket;

import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    @OnEvent("hello")
    public void hello(@RequestBody HelloDto request){
        System.out.println("SocketController.hello");
        System.out.println("request.getMessage() = " + request.getMessage());
    }

}