package com.practice.board.dto.response;

import com.practice.board.entity.Board.Board;
import lombok.*;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public static List<BoardResponse> from(List<Board> boards){
        return boards.stream().map(BoardResponse::from).collect(Collectors.toList());
    }

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .username(board.getUser().getUsername())
                .title(board.getTitle())
                .content(board.getContent())
                .comments(CommentResponse.ListFrom(board.getComments()))
                .build();
    }
}