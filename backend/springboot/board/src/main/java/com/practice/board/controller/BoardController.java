package com.practice.board.controller;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.dto.request.LoginRequest;
import com.practice.board.dto.response.BoardResponse;
import com.practice.board.dto.response.TokensResponse;
import com.practice.board.service.AuthService;
import com.practice.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public void write(@Valid @RequestBody BoardRequest request) {
        boardService.boardPosting(request);
    }

    @PutMapping("/board/{boardId}")
    public void edit(@PathVariable Long boardId, @Valid @RequestBody BoardRequest request) {
        boardService.boardEdit(boardId, request);
    }

    @DeleteMapping("/board/{boardId}")
    public void boardDelete(@PathVariable String boardId, @Valid @RequestBody BoardRequest request) {
        boardService.boardDelete(boardId);
    }

    @GetMapping("/board/all")
    public List<BoardResponse> findBoardAll() {
        return boardService.findBoardAll();
    }

    @GetMapping("/board")
    public List<BoardResponse> findMyBoard() {
        return boardService.findMyBoard();
    }

    @GetMapping("/board/{title}")
    public List<BoardResponse> searchBoard(@PathVariable String title){
        return boardService.searchBoard(title);
    }
}