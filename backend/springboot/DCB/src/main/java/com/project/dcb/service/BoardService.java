package com.project.dcb.service;

import com.project.dcb.Entity.Board.Board;
import com.project.dcb.Entity.Board.BoardRepository;
import com.project.dcb.Entity.Gathering;
import com.project.dcb.Entity.user.User;
import com.project.dcb.Entity.user.UserRepository;
import com.project.dcb.dto.request.BoardRequest;
import com.project.dcb.dto.response.BoardResponse;
import com.project.dcb.exception.InvalidTokenException;
import com.project.dcb.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void writeClassBoard(BoardRequest request) {
        User user = nowUser();
        boardRepository.save(Board.builder()
                .gathering(user.getGathering())
                .username(user.getUsername())
                .title(request.getTitle())
                .content(request.getContent())
                .build()
        );
    }

    public void writeGeneralBoard(BoardRequest request) {
        User user = nowUser();
        boardRepository.save(Board.builder()
                .gathering(Gathering.GENERAL)
                .username(user.getUsername())
                .title(request.getTitle())
                .content(request.getContent())
                .build()
        );
    }

    public List<BoardResponse> findClassBoard() {
        User user = nowUser();
        return boardRepository.findByGathering(user.getGathering()).stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    public List<BoardResponse> findGeneralBoard() {
        User user = nowUser();
        return boardRepository.findByGathering(Gathering.GENERAL).stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    public User nowUser(){
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findByUsername)
                .orElseThrow(InvalidTokenException::new);
    }


}