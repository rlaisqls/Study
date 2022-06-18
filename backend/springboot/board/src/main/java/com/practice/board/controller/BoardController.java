package com.practice.board.controller;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.dto.response.BoardIdResponse;
import com.practice.board.dto.response.BoardResponse;
import com.practice.board.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board") //글 작성
    public BoardIdResponse writeBoard(@Valid @RequestBody BoardRequest request) {
        return boardService.writeBoard(request);
    }

    @PatchMapping ("/board/{boardId}") //글 수정
    public void modifyBoard(@PathVariable Long boardId, @Valid @RequestBody BoardRequest request) {
        boardService.modifyBoard(boardId, request);
    }

    @DeleteMapping("/board/{boardId}") //글 삭제
    public void deleteBoard(@PathVariable Long boardId, @Valid @RequestBody BoardRequest request) {
        boardService.deleteBoard(boardId);
    }

    @GetMapping("/board/{boardId}") //글 조회
    public BoardResponse findBoard(@PathVariable Long boardId) {
        return boardService.findBoard(boardId);
    }

    @GetMapping("/board/all") //전체 글 조회
    public Page<BoardResponse> findBoardAll(Pageable pageable) {
        return boardService.findBoardAll(pageable);
    }

    @GetMapping("/board") //내 글 조회
    public Page<BoardResponse> findMyBoard(@PageableDefault Pageable pageable) {
        return boardService.findMyBoard(pageable);
    }

    @GetMapping("/board/search/{title}") //글 제목으로  검색
    public Page<BoardResponse> searchBoard(@PathVariable String title
            , @PageableDefault Pageable pageable){
        return boardService.searchBoard(title, pageable);
    }
}