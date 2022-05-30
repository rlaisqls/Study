package com.example.ss.service.show;


import com.example.ss.entity.BoardRepository;
import com.example.ss.payload.response.BoardShowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardShowServiceImpl implements BoardShowService{
    private final BoardRepository boardRepository;

    @Override
    public List<BoardShowResponse.Board> show(){
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardShowResponse.Board(board.getId(),board.getTitle(),board.getContent(),board.getCreatedAt()))
                .collect(Collectors.toList());
    }
}