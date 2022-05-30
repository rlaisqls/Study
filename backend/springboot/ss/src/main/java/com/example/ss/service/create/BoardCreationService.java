package com.example.ss.service.create;


import com.example.ss.payload.response.BoardWriteResponse;

public interface BoardCreationService {
    BoardWriteResponse createBoard(String title, String content);
}