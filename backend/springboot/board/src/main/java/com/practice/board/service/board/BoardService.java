package com.practice.board.service.board;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.dto.response.BoardResponse;
import com.practice.board.dto.response.BoardIdResponse;
import com.practice.board.entity.Board.Board;
import com.practice.board.entity.Board.BoardRepository;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.HandleAccessDeniedException;
import com.practice.board.exception.InvalidTokenException;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    //게시글 작성
    public BoardIdResponse boardWrite(BoardRequest request) {
        return new BoardIdResponse(boardRepository.save(Board.builder()
                .user(currentUser())
                .title(request.getTitle())
                .content(request.getContent())
                .build()
        ).getId());
    }

    //게시글 수정
    public void boardModify(Long boardId, BoardRequest request) {
        boardWriterCheck(boardId);
        boardRepository.findById(boardId).ifPresent(board -> {
            board.setTitle(request.getTitle());
            board.setContent(request.getContent());
            boardRepository.save(board);
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
        board.filter(b -> b.getUser().getUuid().equals(currentUser().getUuid()))
                .orElseThrow(()->HandleAccessDeniedException.EXCEPTION);
    }

    //게시글 ID로 조회
    public BoardResponse findBoard(Long id) {
        return boardRepository.findById(id).map(BoardResponse::new)
                .orElseThrow(()->HandleAccessDeniedException.EXCEPTION);
    }

    //전체 게시글 조회
    public Page<BoardResponse> findBoardAll(Pageable pageable) {
        return boardRepository.findAll(pageable)
                .map(BoardResponse::new);
    }

    //내 게시글 조회
    public Page<BoardResponse> findMyBoard(Pageable pageable) {
        return SecurityUtil.getCurrentUsername()
                .map(user->boardRepository.findByUser_Username(user, pageable))
                .map(boards -> boards.map(BoardResponse::new))
                .orElseThrow(()->InvalidTokenException.EXCEPTION);
    }

    //게시글 제목 검색
    public Page<BoardResponse> searchBoard(String title, Pageable pageable) {
        return boardRepository.findByTitleContaining(title, pageable)
                .map(BoardResponse::new);
    }

    //유저정보 토큰에서 받기
    public User currentUser() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(()->InvalidTokenException.EXCEPTION);
    }
}