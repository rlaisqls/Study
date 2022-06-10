package com.practice.board.service;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.dto.response.BoardResponse;
import com.practice.board.dto.response.BoardIdResponse;
import com.practice.board.entity.Board.Board;
import com.practice.board.entity.Board.BoardRepository;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.InvalidTokenException;
import com.practice.board.exception.WrongApproachException;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    //게시글 작성
    public BoardIdResponse boardWrite(BoardRequest request) {
        return new BoardIdResponse(boardRepository.save(Board.builder()
                .user(nowUser())
                .title(request.getTitle())
                .content(request.getContent())
                .build()
        ).getId());
    }

    //게시글 수정
    public void boardModify(Long boardId, BoardRequest request) {
        boardWriterCheck(boardId);
        boardRepository.findById(boardId).ifPresent(selectDocument -> {
            selectDocument.setTitle(request.getTitle());
            selectDocument.setContent(request.getContent());
            boardRepository.save(selectDocument);
        });
    }

    //게시글 삭제
    public void boardDelete(Long boardId) {
        boardWriterCheck(boardId);
        boardRepository.deleteById(boardId);
    }

    //본인 게시글인지 확인
    public void boardWriterCheck(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        board.filter(b -> b.getUser().getUuid().equals(nowUser().getUuid()))
                .orElseThrow(WrongApproachException::new);
    }

    //게시글 조회
    public BoardResponse findBoard(Long id) {
        return boardRepository.findById(id).map(BoardResponse::from)
                .orElseThrow(WrongApproachException::new);
    }

    //전체 게시글 조회
    public List<BoardResponse> findBoardAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }

    //내 게시글 조회
    public List<BoardResponse> findMyBoard() {
        return SecurityUtil.getCurrentUsername()
                .map(boardRepository::findByUser_Username)
                .map(BoardResponse::from)
                .orElseThrow(InvalidTokenException::new);
                    //얘가 SecurityUtill.getCurrentUsername에 대한 else가 맞을까?
    }

    //게시글 제목 검색
    public List<BoardResponse> searchBoard(String title) {
        return boardRepository.findByTitleContaining(title).stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }

    //유저정보 토큰에서 받기
    public User nowUser() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(InvalidTokenException::new);
    }
}