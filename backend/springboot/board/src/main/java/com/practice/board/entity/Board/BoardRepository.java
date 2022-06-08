package com.practice.board.entity.Board;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUser_Username(String username);
    List<Board> findByTitleContaining(String title);

}