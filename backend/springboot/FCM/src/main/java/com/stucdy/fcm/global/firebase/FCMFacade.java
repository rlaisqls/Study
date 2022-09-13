package com.stucdy.fcm.global.firebase;


import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.stucdy.fcm.domain.chat.domain.Chat;
import com.stucdy.fcm.domain.chat.domain.Room;
import com.stucdy.fcm.domain.project.domain.Project;
import com.stucdy.fcm.domain.project.domain.ProjectInvitation;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.global.exception.FCMException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FCMFacade {

    @Async
    public void sendNotification(User user, Chat chat) {

        Room room = chat.getRoom();

        Message message = Message
                .builder()
                .setToken(user.getDeviceToken())
                .setTopic(room.getId().toString())
                .setNotification(
                        Notification
                                .builder()
                                .setTitle(room.getRoomName(chat) + " " + chat.getUser().getName())
                                .setBody(chat.getMessage())
                                .build())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Async
    public void sendNotification(User user, ProjectInvitation projectInvitation) {

        Project project = projectInvitation.getProject();

        Message message = Message
                .builder()
                .setToken(user.getDeviceToken())
                .setNotification(
                        Notification
                                .builder()
                                .setImage(project.getLogoImage())
                                .setTitle(project.getName()  + "에 초대받았습니다")
                                .setBody("초대를 수락하고 프로젝트에 참여해보세요!")
                                .build())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void subscribeTopic(User user, Long roomId) {
        subscribeTopicInFirebase(user, roomId, true);
    }

    public void unsubscribeTopic(User user, Long roomId) {
        subscribeTopicInFirebase(user, roomId, false);
    }

    private void subscribeTopicInFirebase(User user, Long roomId, boolean isSubscribing) {

        FirebaseMessaging instance = FirebaseMessaging.getInstance(FirebaseApp.getInstance());

        try {
            if (isSubscribing) {
                instance.subscribeToTopic(List.of(user.getDeviceToken()), roomId.toString());
            } else {
                instance.unsubscribeFromTopic(List.of(user.getDeviceToken()), roomId.toString());
            }
        } catch (Exception e) {
            throw FCMException.EXCEPTION;
        }
    }

}