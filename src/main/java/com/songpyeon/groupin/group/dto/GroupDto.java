package com.songpyeon.groupin.group.dto;

import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.group.domain.Groupin;
import lombok.Data;


@Data
public class GroupDto {


    private String groupName;
    private User groupLeader;

    public Groupin toEntity(){

        return Groupin.builder()
                .groupName(groupName)
                .groupLeader(groupLeader)
                .build();

    }

}
