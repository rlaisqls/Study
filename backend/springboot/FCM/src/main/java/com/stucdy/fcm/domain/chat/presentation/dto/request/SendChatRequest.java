package com.stucdy.fcm.domain.chat.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendChatRequest {

    private Long roomId;

    private String message;
}