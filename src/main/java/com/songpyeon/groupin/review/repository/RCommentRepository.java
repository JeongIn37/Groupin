package com.songpyeon.groupin.review.repository;


import com.songpyeon.groupin.review.domain.RComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RCommentRepository extends JpaRepository<RComment, Integer> {
    List<RComment> findAllByReviewId(int reviewId);
    RComment findByReviewIdAndId(int reviewId, int rCommentId);
}
