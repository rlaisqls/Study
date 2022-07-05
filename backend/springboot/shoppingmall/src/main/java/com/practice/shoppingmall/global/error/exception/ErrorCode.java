package com.practice.shoppingmall.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //상품
    ITEM_NOT_FOUND(404, "Item Not Found"),
    NOT_ENOUGH_STOCK(404, "Not Enough Stock" ),
    OUT_OF_STOCK(404, "Out Of Stock"),

    //주문
    ORDER_NOT_FOUND(404, "Order Not Found"),

    //유저
    BAD_USER_INFORMATION(404, "Bad User Information"),
    FORBIDDEN_USER(403, "Forbidden User"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    USER_NOT_FOUND(404,"User Nor Found"),

    //요청 형식 오류
    INVALID_PARAMETER(400, "Invalid Parameter"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    //토큰
    EXPIRED_TOKEN(401 , "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),


    BAD_AUTHENTICATION_CODE(400, "Bad Authentication Code"),
    MAIL_SEND_FAIL(404, "Mail Send Fail"),

    INTERNAL_SERVER_ERROR(500,"Server Error");

    private final Integer status;
    private final String message;
}