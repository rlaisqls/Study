package com.practice.board.controller;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.dto.response.BoardIdResponse;
import com.practice.board.dto.response.BoardResponse;
import com.practice.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board") //글 작성
    public BoardIdResponse boardWrite(@Valid @RequestBody BoardRequest request) {
        return boardService.boardWrite(request);
    }

    @PatchMapping ("/board/{boardId}") //글 수정
    public void boardModify(@PathVariable Long boardId, @Valid @RequestBody BoardRequest request) {
        boardService.boardModify(boardId, request);
    }

    @DeleteMapping("/board/{boardId}") //글 삭제
    public void boardDelete(@PathVariable Long boardId, @Valid @RequestBody BoardRequest request) {
        boardService.boardDelete(boardId);
    }

    @GetMapping("/board/{boardId}") //글 조회
    public BoardResponse findBoard(@PathVariable Long boardId) {
        return boardService.findBoard(boardId);
    }

    @GetMapping("/board/all") //전체 글 조회
    public List<BoardResponse> findBoardAll() {
        return boardService.findBoardAll();
    }

    @GetMapping("/board") //내 글 조회
    public List<BoardResponse> findMyBoard() {
        return boardService.findMyBoard();
    }

    @GetMapping("/board/search/{title}") //글 제목으로  검색
    public List<BoardResponse> searchBoard(@PathVariable String title){
        return boardService.searchBoard(title);
    }
}