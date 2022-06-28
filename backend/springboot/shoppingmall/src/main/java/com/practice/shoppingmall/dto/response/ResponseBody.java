package com.practice.shoppingmall.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ResponseBody {

    private final Integer status;
    private final LocalDateTime timestamp;
    private final Response response;


    public static ResponseBody of(Response response, Integer status) {
        return ResponseBody.builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .response(response)
                .build();
    }
}