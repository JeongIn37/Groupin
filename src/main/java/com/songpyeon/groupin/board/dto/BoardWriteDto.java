package com.songpyeon.groupin.board.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class BoardWriteDto {

    private User user;
    private String category;
    private String title;
    private String region;
    @Column(name="max_participants")
    private String maxParticipants;
    @Column(name="group_info")
    private String groupInfo;
    private MultipartFile imageFile;
    //private String group_image_url;
    private String recommend;
    @Column(name="group_notice")
    private String groupNotice;





    public Board toEntity(String groupImageUrl){  // User user 추가 필요

        return Board.builder()
                .category(category)
                .title(title)
                .region(region)
                .maxParticipants(maxParticipants)
                .groupInfo(groupInfo)
                .recommend(recommend)
                .groupNotice(groupNotice)
                .groupImageUrl(groupImageUrl)
                .user(user)
                .build();

    }

}
