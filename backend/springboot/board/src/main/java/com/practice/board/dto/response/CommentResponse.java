package com.practice.board.dto.response;

import com.practice.board.entity.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommentResponse {
    private String username;
    private String comment;

    public static List<CommentResponse> ListFrom(List<Comment> comments) {
        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    public CommentResponse(Comment comment) {
        this.username = comment.getUsername();
        this.comment = comment.getComment();
    }
}