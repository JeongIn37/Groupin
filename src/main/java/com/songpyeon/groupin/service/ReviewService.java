package com.songpyeon.groupin.service;

import com.songpyeon.groupin.entity.Review;
import com.songpyeon.groupin.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    //글 작성
    public void write (Review review){
        reviewRepository.save(review);
    }

    //게시글 리스트 처리
    public List<Review> reviewList(){
        return reviewRepository.findAll();
    }

    //게시글 상세페이지
    public Review review(Integer review_id){
        return reviewRepository.findById(review_id).get();
    }

    //게시글 삭제
    public void reviewDelete(Integer review_id){
        reviewRepository.deleteById(review_id);
    }

}
