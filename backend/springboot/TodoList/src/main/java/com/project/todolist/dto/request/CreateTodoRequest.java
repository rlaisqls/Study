package com.project.todolist.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTodoRequest {

    @NotNull(message = "TODO 제목을 입력하세요.")
    private String title;

    @NotNull(message = "TODO 내용을 입력하세요.")
    private String content;

}