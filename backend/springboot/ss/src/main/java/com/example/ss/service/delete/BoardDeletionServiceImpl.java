package com.example.ss.service.delete;


import com.example.ss.entity.Board;
import com.example.ss.entity.BoardRepository;
import com.example.ss.payload.response.BoardWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDeletionServiceImpl implements BoardDeletionService{
    private final BoardRepository boardRepository;

    @Override
    public BoardWriteResponse deleteBoard(Long id){
        Board board = boardRepository.getById(id);

        boardRepository.deleteById(id);

        return new BoardWriteResponse   ("feed delete success", BoardWriteResponse.Board.builder()
                .id(id)
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build());
    }
}