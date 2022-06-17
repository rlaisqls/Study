package com.practice.board.dto.response;

import com.practice.board.entity.Board.Board;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponse {
    @NotNull
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private List<CommentResponse> comments;

    public BoardResponse(Board board) {
        id = board.getId();
        username = board.getUser().getUsername();
        title = board.getTitle();
        content = board.getContent();
        comments = CommentResponse.ListFrom(board.getComments());
    }
}