package com.songpyeon.groupin.board.repository;

import com.songpyeon.groupin.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {  //<오브젝트, pk의 타입>

    // JPA query method
}