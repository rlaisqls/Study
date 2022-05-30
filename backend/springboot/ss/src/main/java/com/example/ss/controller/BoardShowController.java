package com.example.ss.controller;

import com.example.ss.payload.response.BoardShowResponse;
import com.example.ss.service.show.BoardShowServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BoardShowController {
    private final BoardShowServiceImpl boardSearchService;
    @GetMapping("/feed")
    public BoardShowResponse show(){
        return new BoardShowResponse("feed read access", boardSearchService.show());
    }
}