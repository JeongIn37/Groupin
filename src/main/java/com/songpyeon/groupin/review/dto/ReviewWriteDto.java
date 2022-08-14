package com.songpyeon.groupin.review.dto;
import com.songpyeon.groupin.User.entity.User;
import com.songpyeon.groupin.review.domain.Review;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ReviewWriteDto {

    private User user;
    @NotBlank
    private String title;
    @NotBlank
    private String content;


    public Review toEntity(String groupImageUrl){
        return Review.builder()
                .title(title)
                .content(content)
                .groupImageUrl(groupImageUrl)
                .user(user)
                .build();
    }
}
