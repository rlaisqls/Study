package com.practice.board.controller;

import com.practice.board.dto.request.CommentRequest;
import com.practice.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public void CommentWrite(@RequestBody CommentRequest commentRequest){

        System.out.println(commentRequest.getComment()+" "+commentRequest.getBoardId());
        commentService.CommentWrite(commentRequest);
    }
}