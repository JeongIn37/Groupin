package com.songpyeon.groupin.board.dto;

import com.songpyeon.groupin.board.domain.Board;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class BoardWriteDto {

    private String category;
    private String title;
    private String region;
    private String max_participants;
    private String group_info;
    private MultipartFile image_file;
    //private String group_image_url;
    private String recommend;
    private String group_notice;

    public Board toEntity(String group_image_url){  // User user 추가 필요

        return Board.builder()
                .category(category)
                .title(title)
                .region(region)
                .max_participants(max_participants)
                .group_info(group_info)
                .recommend(recommend)
                .group_notice(group_notice)
                .group_image_url(group_image_url)
                //.user(user)   추가 필요
                .build();

    }

}
