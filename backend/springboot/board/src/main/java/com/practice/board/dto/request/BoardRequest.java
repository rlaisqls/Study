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
    @NotNull
    private String title;

    @NotNull
    private String content;

    public static Board toBoard(User user, BoardRequest request){
        return Board.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

}