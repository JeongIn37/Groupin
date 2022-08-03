package com.songpyeon.groupin.board.repository;

import com.songpyeon.groupin.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {  //<오브젝트, pk의 타입>

    // JPA query method
    List<Board> findByCategory(String category);
    Board findByCategoryAndId(String category, int id);
    @Transactional
    @Modifying  // 수정 반영용
    @Query("update Board p set p.views = p.views + 1 where p.id = :id")
    int updateViewCount(int id);

    @Transactional
    @Modifying
    @Query("update Board p set p.comments = p.comments + 1 where p.id = :id")
    int updateCommentCount(int id);

    @Transactional
    @Modifying  // 수정 반영용
    @Query("update Board p set p.comments = p.comments - 1 where p.id = :id")
    int deleteCommentCount(int id);

    // 페이징 + 정렬
    Page<Board> findAllByCategory(String category, Pageable pageable);
}