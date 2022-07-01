package com.project.todolist.dto.response;

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
public class FindTodoResponse {

    private Long todoId;

    private String title;

    private LocalDateTime createdDate;

    private String writer;

    private Boolean isChecked;
}