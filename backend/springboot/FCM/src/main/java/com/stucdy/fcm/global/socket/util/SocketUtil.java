package com.stucdy.fcm.global.socket.util;

import com.corundumstudio.socketio.SocketIOClient;
import com.stucdy.fcm.global.socket.security.ClientProperty;

public class SocketUtil {

    public static String getEmail(SocketIOClient socketIOClient){
        return socketIOClient.get(ClientProperty.USER_KEY);
    }

}