package com.project.todolist.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindTodoInfoResponse {

    private Long todoId;

    private String title;

    private LocalDateTime createdDate;

    private String writer;

    private Boolean isChecked;

    private Boolean isLiked;

    private Integer likeCount;
}