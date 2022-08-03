package com.songpyeon.groupin.repository;

import com.songpyeon.groupin.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
