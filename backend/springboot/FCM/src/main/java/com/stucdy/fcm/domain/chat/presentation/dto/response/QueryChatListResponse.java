package com.stucdy.fcm.domain.chat.presentation.dto.response;

import com.stucdy.fcm.domain.chat.domain.Chat;
import com.stucdy.fcm.domain.user.domain.User;
import com.stucdy.fcm.domain.user.presentation.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryChatListResponse {

    private List<ChatResponse> chatList;

    public static ChatResponse of(Chat chat) {

        User user = chat.getUser();

        return ChatResponse
                .builder()
                .roomId(chat.getRoom().getId())
                .user(UserResponse.of(user))
                .message(chat.getMessage())
                .createdAt(chat.getCreatedAt())
                .build();
    }

    @Builder
    public static class ChatResponse {

        private Long roomId;

        private UserResponse user;

        private LocalDateTime createdAt;

        private String message;
    }
}