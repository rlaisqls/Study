package com.project.dcb.dto.response;

import com.project.dcb.Entity.Board.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse {
    private String title;

    private String name;

    private String content;

    public BoardResponse(Board board) {
        title = board.getTitle();
        name = board.getName();
        content = board.getContent();
    }
}