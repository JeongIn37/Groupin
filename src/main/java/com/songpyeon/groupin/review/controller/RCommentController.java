package com.songpyeon.groupin.review.controller;

import com.songpyeon.groupin.review.domain.RComment;
import com.songpyeon.groupin.review.dto.RCommentDto;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.review.service.RCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RCommentController {

    private final RCommentService rCommentService;

    @GetMapping("/review/{reviewId}/comments")
    public ResponseEntity<?> commentList(@PathVariable int reviewId){
        List<RComment> comment = rCommentService.commentList(reviewId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/review/{reviewId}/writeComment")
    public ResponseEntity<?> saveComment(@RequestBody RCommentDto rCommentDto, @PathVariable int reviewId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        RComment comment = rCommentService.writeComment(rCommentDto.getContent(),reviewId, principalDetails.getUser().getUser_id());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/review/{reviewId}/{rCommentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable int reviewId, @PathVariable int rCommentId,  @AuthenticationPrincipal PrincipalDetails principalDetails){
        rCommentService.deleteComment(reviewId, rCommentId, principalDetails);
        Map<String, String> map = new HashMap<>();
        map.put("result", "댓글 삭제 완료");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
