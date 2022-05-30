package com.example.ss.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BoardWriteResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("feed")
    private final Board board;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Board{
        private Long id;
        private String title;
        private String content;
        private LocalDate createdAt;
    }
}