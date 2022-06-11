package com.project.dcb.Entity.Board;

import com.project.dcb.Entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByGathering(Gathering gathering);
}