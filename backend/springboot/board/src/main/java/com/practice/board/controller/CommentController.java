package com.practice.board.controller;

import com.practice.board.dto.request.CommentRequest;
import com.practice.board.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/board/{boardId}/comment")
    public void CommentWrite(@PathVariable Long boardId, @RequestBody CommentRequest commentRequest){
        commentService.CommentWrite(boardId, commentRequest);
    }
}