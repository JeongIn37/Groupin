package com.songpyeon.groupin.review.controller;

import com.songpyeon.groupin.User.config.auth.PrincipalDetails;
import com.songpyeon.groupin.review.domain.Review;
import com.songpyeon.groupin.review.dto.ReviewWriteDto;
import com.songpyeon.groupin.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/review/list")
    public ResponseEntity<List<Review>> postList(){
        List<Review> reviewEntity = reviewService.postList();
        return new ResponseEntity<>(reviewEntity, HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<Page<Review>> postsByType(@RequestParam String type, @RequestParam int page){
        Page<Review> pageEntity = reviewService.postsByType(type, page);
        return new ResponseEntity<>(pageEntity, HttpStatus.OK);
    }


    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> postDetail(@PathVariable int reviewId) {
        Review reviewEntity = reviewService.postDetail(reviewId);
        return new ResponseEntity<>(reviewEntity, HttpStatus.OK);
    }

    @PostMapping("/review/write")
    public ResponseEntity<Object> writePost(@RequestPart ReviewWriteDto reviewWriteDto, @RequestPart MultipartFile imageFile, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Review reviewEntity = reviewService.savePost(reviewWriteDto, imageFile, principalDetails);
        return new ResponseEntity<>(reviewEntity, HttpStatus.CREATED);
    }


    @DeleteMapping("/review/{reviewId}/delete")
    public ResponseEntity<Object> deletePost(@PathVariable int reviewId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        reviewService.deletePost(reviewId, principalDetails);
        Map<String, String> map = new HashMap<>();
        map.put("result", "삭제 완료");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/review/{reviewId}/edit")
    public ResponseEntity<Object> editPost(@PathVariable int reviewId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Review reviewEntity = reviewService.editPage(reviewId, principalDetails);
        return new ResponseEntity<>(reviewEntity, HttpStatus.OK);
    }

    @PatchMapping("/review/{reviewId}/edit")
    public ResponseEntity<Object> editPost(@RequestPart Review review, @RequestPart MultipartFile imageFile,@PathVariable int reviewId){
        Review reviewEntity = reviewService.editPost(review, imageFile, reviewId);
        return new ResponseEntity<>(reviewEntity, HttpStatus.OK);
    }

}
