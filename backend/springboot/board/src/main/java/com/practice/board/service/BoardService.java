package com.practice.board.service;

import com.practice.board.dto.request.BoardRequest;
import com.practice.board.dto.response.BoardResponse;
import com.practice.board.entity.Board.BoardRepository;
import com.practice.board.entity.user.User;
import com.practice.board.entity.user.UserRepository;
import com.practice.board.exception.UserNotFoundException;
import com.practice.board.exception.WrongApproachException;
import com.practice.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    public void boardPosting(BoardRequest request) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername)
                .orElseThrow(UserNotFoundException::new);
        boardRepository.save(BoardRequest.toBoard(user,request.getTitle(), request.getContent()));
    }

    public void boardEdit(Long boardId, BoardRequest request) {
        User nowUser = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).get();
        User boardUser = boardRepository.findById(boardId).get().getUser();
        if(nowUser!=boardUser) throw new WrongApproachException();

    }

    public void boardDelete(String boardId) {
    }

    public List<BoardResponse> findBoardAll() {
        return null;
    }

    public List<BoardResponse> findMyBoard() {
        return null;
    }

    public List<BoardResponse> searchBoard(String title) {
        return null;
    }
}