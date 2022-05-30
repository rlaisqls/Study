package com.example.ss.service.show;


import com.example.ss.payload.response.BoardShowResponse;

import java.util.List;

public interface BoardShowService {
    List<BoardShowResponse.Board> show();
}