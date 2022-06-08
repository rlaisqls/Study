package com.practice.board.entity.comment;

import com.practice.board.entity.Board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}