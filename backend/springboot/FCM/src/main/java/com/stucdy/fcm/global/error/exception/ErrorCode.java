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

    PASSWORD_MISMATCH(401, "USER-401", "Password Mismatch"),
    USER_NOT_FOUND(404, "USER-404-1", "Room Not Found"),
    USER_ALREADY_EXIST(409, "USER-409-1", "User Already Exist"),
    FORBIDDEN(403, "USER-403-1", "Forbidden"),

    ROOM_NOT_FOUND(404, "ROOM-404-1", "Room Not Found"),
    ROOM_USER_NOT_FOUND(404, "ROOM-404-2", "Room User Not Found"),

    USER_NOT_INVITED(404, "PROJECT-404", "User Not Invited"),

    FCM_EXCEPTION(404, "FCM-404-1", "FCM Exception"),

    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final Integer status;
    private final String code;
    private final String message;
}