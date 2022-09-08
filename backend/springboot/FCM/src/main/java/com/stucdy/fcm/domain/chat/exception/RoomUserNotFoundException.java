package com.stucdy.fcm.domain.chat.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class RoomUserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RoomUserNotFoundException();
    private RoomUserNotFoundException(){
        super(ErrorCode.ROOM_USER_NOT_FOUND);
    }
}