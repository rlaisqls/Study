package com.practice.board.service;

import com.practice.board.dto.request.CommentRequest;
import com.practice.board.entity.Board.BoardRepository;
import com.practice.board.entity.comment.Comment;
import com.practice.board.entity.comment.CommentRepository;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.BoardNotExistException;
import com.practice.board.exception.WrongApproachException;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public void CommentWrite(CommentRequest request) {
        boardRepository.findById(request.getBoardId())
                .map(board->CommentRequest.toComment(getUser(),board,request))
                .orElseThrow(BoardNotExistException::new);
    }

    public User getUser(){
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(WrongApproachException::new);
    }
}