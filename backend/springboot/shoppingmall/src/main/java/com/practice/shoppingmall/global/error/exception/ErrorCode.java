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
    NOT_ENOUGH_STOCK(409, "Not Enough Stock" ),
    OUT_OF_STOCK(409, "Out Of Stock"),

    //주문
    ORDER_NOT_FOUND(404, "Order Not Found"),
    ALREADY_DELIVERED(409, "Already Delivered"),

    COUPON_NOT_FOUND(404, "Coupon Not Found"),
    INVALID_COUPON(400, "Invalid Coupon"),
    DISCOUNT_OUT_OF_RANGE(400, "Discount Out Of Range"),

    //유저
    BAD_USER_INFORMATION(404, "Bad User Information"),
    FORBIDDEN_USER(403, "Forbidden User"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    USER_NOT_FOUND(404, "User Not Found"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),

    MAIL_SEND_FAIL(404, "Mail Send Fail"),
    UNVERIFIED_EMAIL(401, "UnVerified Email"),
    BAD_AUTHENTICATION_CODE(401, "Bad Authentication Code"),

    //토큰
    EXPIRED_TOKEN(401 , "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),

    //요청 형식 오류
    INVALID_PARAMETER(400, "Invalid Parameter"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    INTERNAL_SERVER_ERROR(500,"Server Error");

    private final Integer statusCode;
    private final String message;
}