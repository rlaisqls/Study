package com.stucdy.fcm.domain.user.facade;

import com.corundumstudio.socketio.SocketIOClient;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.domain.repository.UserRepository;
import com.stucdy.fcm.domain.user.exception.UserNotFoundException;
import com.stucdy.fcm.global.socket.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    public User getCurrentUser(SocketIOClient socketIOClient) {
        return getUserByEmail(SocketUtil.getEmail(socketIOClient));
    }

    public User getUserByEmail(String accountId) {
        return userRepository.findByEmail(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }


}