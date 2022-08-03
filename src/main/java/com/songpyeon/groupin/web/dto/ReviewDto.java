package com.songpyeon.groupin.web.dto;
import com.songpyeon.groupin.entity.Review;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class ReviewDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;


    public Review toEntity(){
        return Review.builder()
                .title(title)
                .content(content)
                .build();
    }
}
