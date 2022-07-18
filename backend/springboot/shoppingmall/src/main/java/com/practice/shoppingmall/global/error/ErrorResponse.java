package com.practice.shoppingmall.global.error;

import com.practice.shoppingmall.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String message;
    private Integer statusCode;
    private LocalDateTime timestamp;
    private String description;

    public static ErrorResponse of(ErrorCode errorCode, String description) {

        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .statusCode(errorCode.getStatusCode())
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }
}