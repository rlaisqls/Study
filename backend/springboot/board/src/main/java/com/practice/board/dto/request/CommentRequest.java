package com.practice.board.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.board.entity.Board.Board;
import com.practice.board.entity.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequest {
    @JsonProperty("board_id")
    private Long boardId;
    private String comment;
}