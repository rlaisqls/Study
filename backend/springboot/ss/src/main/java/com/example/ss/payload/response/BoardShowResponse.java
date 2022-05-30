package com.example.ss.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardShowResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("posts")
    private List<Board> posts;

    @AllArgsConstructor
    @Getter
    public static class Board{
        private Long id;
        private String title;
        private String content;
        private LocalDate createdAt;
    }
}