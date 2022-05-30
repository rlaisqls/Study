package com.example.ss.service.modify;


import com.example.ss.payload.request.BoardRequest;
import com.example.ss.payload.response.BoardWriteResponse;

public interface BoardModificationService {
    BoardWriteResponse modifyBoard(BoardRequest request, Long id);
}