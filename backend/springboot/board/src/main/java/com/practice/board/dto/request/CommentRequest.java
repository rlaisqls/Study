package com.practice.board.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.board.entity.Board.Board;
import com.practice.board.entity.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentRequest {
    @NotNull
    private String comment;
}