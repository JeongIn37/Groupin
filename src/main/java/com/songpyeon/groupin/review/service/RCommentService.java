package com.songpyeon.groupin.review.service;


import com.songpyeon.groupin.User.config.auth.PrincipalDetails;
import com.songpyeon.groupin.review.repository.RCommentRepository;
import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.User.handler.ex.CustomException;
import com.songpyeon.groupin.User.handler.ex.ErrorCode;
import com.songpyeon.groupin.repository.UserRepository;
import com.songpyeon.groupin.review.domain.RComment;
import com.songpyeon.groupin.review.domain.Review;
import com.songpyeon.groupin.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RCommentService {

    private final RCommentRepository rCommentRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public RComment writeComment(String content, int reviewId, int userId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        });
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });

        RComment comment = new RComment();
        comment.setContent(content);
        comment.setReview(review);
        comment.setUser(user);

        reviewRepository.updateCommentCount(reviewId);

        return rCommentRepository.save(comment);
    }

    @Transactional
    public List<RComment> commentList(int reviewId){
        List<RComment> comments = rCommentRepository.findAllByReviewId(reviewId);
        return comments;
    }


    @Transactional
    public void deleteComment(int reviewId, int id, PrincipalDetails principalDetails){
        RComment rComment = rCommentRepository.findByReviewIdAndId(reviewId, id);
        if (rComment == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == rComment.getUser().getUser_id()) {
            reviewRepository.deleteCommentCount(reviewId);
            rCommentRepository.delete(rComment);
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }
    }

}
