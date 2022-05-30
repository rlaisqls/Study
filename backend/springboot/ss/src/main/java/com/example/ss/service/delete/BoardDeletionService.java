package com.example.ss.service.delete;


import com.example.ss.payload.response.BoardWriteResponse;

public interface BoardDeletionService {
    BoardWriteResponse deleteBoard(Long id);
}