package com.stucdy.fcm.domain.chat.exception;

import com.stucdy.fcm.global.error.exception.BusinessException;
import com.stucdy.fcm.global.error.exception.ErrorCode;

public class RoomNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RoomNotFoundException();
    private RoomNotFoundException(){
        super(ErrorCode.ROOM_NOT_FOUND);
    }
}