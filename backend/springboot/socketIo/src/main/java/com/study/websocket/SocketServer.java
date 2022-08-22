package com.study.websocket;

import ch.qos.logback.core.net.server.ServerRunner;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@RequiredArgsConstructor
public class SocketServer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory. getLogger ( ServerRunner. class ) ;

    private final SocketIOServer socketIOServer ;

    @Override
    public void run(String... args) throws Exception {
        socketIOServer.start();
    }
}