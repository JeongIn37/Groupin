package com.songpyeon.groupin.review.dto;

import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.review.domain.Review;
import lombok.Data;

@Data
public class RCommentDto {

    private User user;
    private Review review;
    private String content;
}
