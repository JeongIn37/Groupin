package com.songpyeon.groupin.group.dto;

import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.group.domain.Groupin;
import com.songpyeon.groupin.group.domain.GroupRelation;
import lombok.Data;


@Data
public class GroupRelationDto {

    private User groupMember;
    private Groupin groupin;

    public GroupRelation toEntity(){

        return GroupRelation.builder()
                .groupMember(groupMember)
                .groupin(groupin)
                .build();

    }

}
