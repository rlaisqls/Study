package com.stucdy.fcm.global.firebase;


import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.stucdy.fcm.domain.chat.domain.Chat;
import com.stucdy.fcm.domain.chat.domain.Room;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.global.exception.FCMException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FCMFacade {

    @Async

    @Transactional
    public void sendNotification(User user, Chat chat) {

        Room room = chat.getRoom();

        Message message = Message
                .builder()
                .setToken(user.getDeviceToken())
                .setTopic(room.getId().toString())
                .setNotification(
                        Notification
                                .builder()
                                .setTitle(room.getRoomName() + "" + chat.getUser().getUsername())
                                .setBody(chat.getMessage())
                                .build())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void subscribeTopic(List<User> userList, Long roomId) {
        subscribeTopicInFirebase(userList, roomId, true);
    }

    public void unsubscribeTopic(List<User> userList, Long roomId) {
        subscribeTopicInFirebase(userList, roomId, false);
    }

    private void subscribeTopicInFirebase(List<User> userList, Long roomId, boolean isSubscribing) {

        List<String> deviceTokenList = userList
                .stream()
                .map(User::getDeviceToken)
                .collect(Collectors.toList());

        for (int i = 0; i < userList.size(); i++) {

            FirebaseMessaging instance = FirebaseMessaging.getInstance(FirebaseApp.getInstance());

            try {
                if (isSubscribing) {
                    instance.subscribeToTopic(deviceTokenList, roomId.toString());
                } else {
                    instance.unsubscribeFromTopic(deviceTokenList, roomId.toString());
                }
            } catch (Exception e) {
                throw FCMException.EXCEPTION;
            }
        }
    }

}