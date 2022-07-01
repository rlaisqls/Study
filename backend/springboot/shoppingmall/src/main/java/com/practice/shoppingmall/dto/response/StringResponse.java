package com.practice.shoppingmall.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StringResponse implements Response {

    private final String message;

    @Override
    public String toString() {
        return message;
    }
}