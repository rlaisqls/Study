package com.stucdy.fcm.domain.chat.service;

import com.stucdy.fcm.domain.chat.presentation.dto.response.QueryChatListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyRoomListService {

    @Transactional(readOnly = true)
    public QueryChatListResponse execute() {
        return null;
    }

}