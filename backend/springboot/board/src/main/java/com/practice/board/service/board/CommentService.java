package com.practice.board.service.board;

import com.practice.board.dto.request.CommentRequest;
import com.practice.board.entity.Board.BoardRepository;
import com.practice.board.entity.comment.Comment;
import com.practice.board.entity.comment.CommentRepository;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.BoardNotExistException;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    //댓글 작성
    public void CommentWrite(Long boardId, CommentRequest request) {
        Comment comment = boardRepository.findById(boardId)
                .map(board -> Comment.builder()
                        .username(SecurityUtil.getCurrentUsername().get())
                        .board(board)
                        .comment(request.getComment())
                        .build())
                .orElseThrow(()->BoardNotExistException.EXCEPTION);
        commentRepository.save(comment);
    }
}