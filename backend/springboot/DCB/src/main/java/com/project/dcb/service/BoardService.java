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

    //게시글 작성
    public void writeClassBoard(BoardRequest request) {
        User user = currentUser();
        writeBoard(user.getGathering(), user, request);
    }

    public void writeGeneralBoard(BoardRequest request) {
        User user = currentUser();
        writeBoard(Gathering.GENERAL, user, request);
    }

    private void writeBoard(Gathering gathering, User user, BoardRequest request) {
        boardRepository.save(Board.builder()
                .gathering(gathering)
                .name(user.getName())
                .title(request.getTitle())
                .content(request.getContent())
                .build()
        );
    }

    //게시글 조회
    public List<BoardResponse> findClassBoard() {
        return findBoard(currentUser().getGathering());
    }

    public List<BoardResponse> findGeneralBoard() {
        return findBoard(Gathering.GENERAL);
    }

    public List<BoardResponse> findBoard(Gathering gathering) {
        return boardRepository.findByGathering(gathering).stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    //현재 유저 가져오기
    public User currentUser() {
        return userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .orElseThrow(InvalidTokenException::new);
    }

}