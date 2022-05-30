package com.example.ss.service.modify;

import com.example.ss.entity.BoardRepository;
import com.example.ss.exception.BoardNotExistException;
import com.example.ss.payload.request.BoardRequest;
import com.example.ss.payload.response.BoardWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BoardModificationServiceImpl implements BoardModificationService{
    private final BoardRepository boardRepository;

    @Override
    public BoardWriteResponse modifyBoard(BoardRequest request, Long id){
        boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(request.getTitle());
                    board.setContent(request.getContent());
                    board.setCreatedAt(LocalDate.now());
                    boardRepository.save(board);
                    return board;
                })
                .orElseThrow(()->new BoardNotExistException(id));
        return new BoardWriteResponse("feed update success",BoardWriteResponse.Board.builder()
                .id(id)
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDate.now())
                .build());
    }

}