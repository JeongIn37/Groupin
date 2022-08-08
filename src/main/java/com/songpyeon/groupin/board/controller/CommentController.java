package com.songpyeon.groupin.board.controller;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.domain.Comment;
import com.songpyeon.groupin.board.dto.BoardWriteDto;
import com.songpyeon.groupin.board.dto.CommentDto;
import com.songpyeon.groupin.board.service.CommentService;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{category}/{boardId}/comments")
    public ResponseEntity<?> commentList(@PathVariable String category, @PathVariable int boardId){
        List<Comment> comment = commentService.commentList(category, boardId);
        return new ResponseEntity<>(new CMRespDto<>(1, "댓글 보기", comment), HttpStatus.OK);
    }


    @PostMapping("/{category}/{boardId}/writeComment")
    public ResponseEntity<?> saveComment(CommentDto commentDto, @PathVariable String category, @PathVariable int boardId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Comment comment = commentService.writeComment(commentDto.getContent(), category, boardId, principalDetails.getUser().getUserId());
        return new ResponseEntity<>(new CMRespDto<>(1, "댓글 등록 성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{category}/{boardId}/{gCommentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable String category, @PathVariable int boardId, @PathVariable int gCommentId,  @AuthenticationPrincipal PrincipalDetails principalDetails){
        commentService.deleteComment(category, boardId, gCommentId, principalDetails);
        Map<String, String> map = new HashMap<>();
        map.put("result", "댓글 삭제 완료");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
