package com.stucdy.fcm.domain.chat.presentation;

import com.stucdy.fcm.domain.chat.presentation.dto.response.QueryChatListResponse;
import com.stucdy.fcm.domain.chat.service.QueryChatLIstService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/chats")
@RestController
public class ChatController {

    private final QueryChatLIstService queryChatLIstService;

    @GetMapping("/{room-id}")
    public QueryChatListResponse queryChatList(@PathVariable("room-id") Long roomId, Pageable pageable) {
        return queryChatLIstService.execute(roomId, pageable);
    }

}