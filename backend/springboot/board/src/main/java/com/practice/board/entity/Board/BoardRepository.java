package com.practice.board.entity.Board;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    /* 그냥 list로 받을 수도 있는데, page는 페이지를 카운트 해주는 쿼리가 같이 전달됨
    그리고 slice는 전체 카운트 말고 다음 페이지가 있는지만 확인해줌*/
    Page<Board> findByUser_Username(String username, Pageable pageable);
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    Page<Board> findAll(Pageable pageable);

}