package com.practice.shoppingmall.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //요청 형식 오류
    INVALID_PARAMETER(400, "Invalid Parameter"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    //토큰
    EXPIRED_TOKEN(401 , "Expired Token"),
    INCORRECT_TOKEN(400, "Incorrect Token"),
    INVALID_TOKEN(401, "Invalid Token"),

    //권한없음
    HANDLE_ACCESS_DENIED(403, "Handle Access Denied"),

    //유저
    USER_NOT_FOUND(404,"User Nor Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    INVALID_INFORMATION(401, "Invalid Information"),
    PASSWORD_MISMATCH(404, "Password Mismatch"),

    //상품
    ITEM_NOT_FOUND(404, "Item Not Found"),

    //서버문제
    INTERNAL_SERVER_ERROR(500,"Server Error")

    //주문
    , OUT_OF_STOCK(404, "Out Of Stock"),
    ORDER_NOT_FOUND(404, "Order Not Found");

    private final Integer status;
    private final String message;
}