package com.practice.board.dto.request;

import com.practice.board.entity.Board.Board;
import com.practice.board.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BoardRequest {
    @NotNull(message = "제목을 입력하세요.")
    private String title;

    @NotNull(message = "내용을 입력하세요.")
    private String content;

}