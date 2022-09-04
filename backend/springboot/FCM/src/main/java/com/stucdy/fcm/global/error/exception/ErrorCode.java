package com.stucdy.fcm.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    EXPIRED_TOKEN(401,"AUTH-401-1", "Expired Token" ),
    INVALID_TOKEN(401,"AUTH-401-2","Invalid Token"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),

    ROOM_NOT_FOUND(404, "ROOM-404-1", "Room Not Found"),

    FCM_EXCEPTION(404, "FCM-404-1", "FCM Exception"),

    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final Integer status;
    private final String code;
    private final String message;
}