package com.songpyeon.groupin.board.dto;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.domain.Comment;
import com.songpyeon.groupin.user.User;
import lombok.Data;

@Data
public class CommentDto {

    private User user;
    private String category;
    private Board board;
    private String content;

    public Comment toEntity(){  // User user 추가 필요

        return Comment.builder()
                .user(user)
                .category(category)
                .board(board)
                .content(content)
                .build();

    }

}
