package com.practice.board.dto.request;

import com.practice.board.entity.Board.Board;
import com.practice.board.entity.comment.Comment;
import com.practice.board.entity.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Long boardId;
    private String comment;

    public static Comment toComment(User user, Board board, CommentRequest request) {
        return Comment.builder()
                .user(user)
                .board(board)
                .comment(request.getComment())
                .build();
    }
}