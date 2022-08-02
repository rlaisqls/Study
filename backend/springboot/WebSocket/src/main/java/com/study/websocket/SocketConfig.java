package com.study.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SocketConfig {

    private static final Logger logger = LoggerFactory.getLogger(SocketConfig.class);

    @Value("${socketio.port}")
    private Integer port;

    @Bean("socketIOServer")
    public SocketIOServer socketIOServer() {

        com.corundumstudio.socketio.SocketConfig socketConfig = new com.corundumstudio.socketio.SocketConfig();
        socketConfig.setReuseAddress(true);

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setPort(port);
        config.setSocketConfig(socketConfig);
        config.setOrigin("*");
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}