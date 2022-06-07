package com.practice.board.controller.Board;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WriteController {

    private final BoardService boardService;


}