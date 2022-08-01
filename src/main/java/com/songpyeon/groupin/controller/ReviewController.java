package com.songpyeon.groupin.controller;

import com.songpyeon.groupin.entity.Review;
import com.songpyeon.groupin.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/write")
    public String reviewWrite(Review review){
        reviewService.write(review);
        return "";
    }

    @GetMapping("/review/list")
    public String reviewList(Model model){
        model.addAttribute("list", reviewService.reviewList());
        return "";
    }

    @GetMapping("/review/{review_id}")
    public String review(Model model, @PathVariable("review_id") Integer review_id){
        model.addAttribute("board", reviewService.review(review_id));
        return "";
    }

    @GetMapping("/review/delete")
    public String reviewDelete(Integer review_id){
        reviewService.reviewDelete(review_id);
        return "";
    }

    @GetMapping("/review/{review_id}/edit")
    public String reviewEdit(Model model, @PathVariable("review_id") Integer review_id) {
        model.addAttribute("review", reviewService.review(review_id));
        return "";
    }

    @PostMapping("/review/{review_id}/update")
    public String reviewUpdate(@PathVariable("review_id") Integer review_id, Review review){

        Review reviewTemp = reviewService.review(review_id);
        reviewTemp.setTitle(review.getTitle());
        reviewTemp.setContent(review.getContent());

        reviewService.write(reviewTemp);
        return "";
    }
}
