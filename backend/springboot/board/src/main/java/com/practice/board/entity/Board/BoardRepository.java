package com.practice.board.entity.Board;

import com.practice.board.entity.refeshToken.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findByUser_Username(String username);
    List<Board> findByTitleContaining(String title);

}