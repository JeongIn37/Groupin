package com.songpyeon.groupin.board.repository;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {  //<오브젝트, pk의 타입>

    // JPA query method
    List<Board> findByCategory(String category);
    Board findByCategoryAndId(String category, int id);

}