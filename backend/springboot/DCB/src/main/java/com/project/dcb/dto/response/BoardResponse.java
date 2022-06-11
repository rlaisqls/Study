package com.project.dcb.dto.response;

import com.project.dcb.Entity.Board.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse {
    private String title;

    private String username;

    private String content;
    public BoardResponse(Board board){
        title = board.getTitle();
        username = board.getUsername();
        content = board.getContent();
    }
}