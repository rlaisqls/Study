package com.stucdy.fcm.global.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.stucdy.fcm.domain.user.facade.UserFacade;
import com.stucdy.fcm.global.security.jwt.JwtTokenProvider;
import com.stucdy.fcm.global.socket.security.ClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SocketConnectListener {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {

        String token = jwtTokenProvider.resolveToken(socketIOClient);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        String email = authentication.getName();

        socketIOClient.set(ClientProperty.USER_KEY, email);
    }

}