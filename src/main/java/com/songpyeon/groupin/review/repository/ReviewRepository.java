package com.songpyeon.groupin.review.repository;

import com.songpyeon.groupin.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Transactional
    @Modifying
    @Query("update Review p set p.views = p.views + 1 where p.id = :id")
    int updateViewCount(int id);

    @Transactional
    @Modifying
    @Query("update Review p set p.comments = p.comments + 1 where p.id = :id")
    int updateCommentCount(int id);

    @Transactional
    @Modifying
    @Query("update Review p set p.comments = p.comments - 1 where p.id = :id")
    int deleteCommentCount(int id);

}
