package com.songpyeon.groupin.board.repository;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByCategoryAndBoardId(String category, int boardId);
    Comment findByBoardIdAndId(int boardId, int gCommentId);

}
