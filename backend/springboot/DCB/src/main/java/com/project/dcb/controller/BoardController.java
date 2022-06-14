package com.project.dcb.controller;

import com.project.dcb.dto.request.BoardRequest;
import com.project.dcb.dto.request.CalendarRequest;
import com.project.dcb.dto.response.BoardResponse;
import com.project.dcb.dto.response.CalendarResponse;
import com.project.dcb.dto.response.ResultListResponse;
import com.project.dcb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/admin/board")
    public ResponseEntity<Object> writeGeneralBoard(@RequestBody BoardRequest request){
        boardService.writeGeneralBoard(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/user/board")
    public ResponseEntity<Object> writeClassBoard(@RequestBody BoardRequest request){
        boardService.writeClassBoard(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/board")
    public ResponseEntity<Object> findGeneralBoard(){
        return new ResponseEntity<>
                (new ResultListResponse<>(boardService.findGeneralBoard()), HttpStatus.OK);
    }

    @GetMapping("/user/board")
    public ResponseEntity<Object> findClassBoard(){
        return new ResponseEntity<>
                (new ResultListResponse<>(boardService.findClassBoard()), HttpStatus.OK);
    }

}