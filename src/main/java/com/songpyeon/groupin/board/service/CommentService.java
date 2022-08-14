package com.songpyeon.groupin.board.service;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.domain.Comment;
import com.songpyeon.groupin.board.repository.BoardRepository;
import com.songpyeon.groupin.board.repository.CommentRepository;
import com.songpyeon.groupin.User.config.auth.PrincipalDetails;
import com.songpyeon.groupin.User.entity.User;
import com.songpyeon.groupin.User.handler.ex.CustomException;
import com.songpyeon.groupin.User.handler.ex.ErrorCode;
import com.songpyeon.groupin.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Comment writeComment(String content, String category, int boardId, int userId){

        Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        });
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setCategory(category);
        comment.setBoard(board);

        boardRepository.updateCommentCount(boardId);
        userRepository.writingCommentsPoint(userId);

        return commentRepository.save(comment);

    }

    @Transactional
    public List<Comment> commentList(String category, int boardId){
        List<Comment> comments = commentRepository.findAllByCategoryAndBoardId(category, boardId);
        return comments;
    }


    @Transactional
    public void deleteComment(String category, int boardId, int id, PrincipalDetails principalDetails){
        Comment comment = commentRepository.findByBoardIdAndId(boardId, id);
        if (comment == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == comment.getUser().getUser_id()) {
            boardRepository.deleteCommentCount(boardId);
            commentRepository.delete(comment);
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }
    }

}
