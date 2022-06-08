package com.practice.board.service;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.dto.response.BoardResponse;
import com.practice.board.entity.Board.Board;
import com.practice.board.entity.Board.BoardRepository;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.UserNotFoundException;
import com.practice.board.exception.WrongApproachException;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    public void boardWrite(BoardRequest request) {
        boardRepository.save(BoardRequest.toBoard(getUser(),request));
    }

    public void boardModify(Long boardId, BoardRequest request) {
        boardWriterCheck(boardId);
        boardRepository.findById(boardId).ifPresent(selectDocument->{
                    selectDocument.setTitle(request.getTitle());
                    selectDocument.setContent(request.getContent());
                    boardRepository.save(selectDocument);});
    }

    public void boardDelete(Long boardId) {
        boardWriterCheck(boardId);
        boardRepository.deleteById(boardId);
    }

    public User getUser(){
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(WrongApproachException::new);
    }

    public void boardWriterCheck(Long boardId){
        Optional<Board> board = boardRepository.findById(boardId);
        board.filter(b -> b.getUser().getUuid()
                        .equals(getUser().getUuid()));
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> findBoardAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }

    public BoardResponse boardShow(Long id) {
        return boardRepository.findById(id).map(BoardResponse::from)
                .orElseThrow(WrongApproachException::new);
    }

    public List<BoardResponse> findMyBoard() {
        return SecurityUtil.getCurrentUsername()
                .map(boardRepository::findByUser_Username)
                .map(BoardResponse::from)
                .orElse(null);
    }

    public List<BoardResponse> searchBoard(String title) {
        return boardRepository.findByTitleContaining(title).stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }
}