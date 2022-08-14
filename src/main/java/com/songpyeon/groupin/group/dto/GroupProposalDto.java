package com.songpyeon.groupin.group.dto;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.User.entity.User;
import com.songpyeon.groupin.group.domain.GroupProposal;
import lombok.Data;

@Data
public class GroupProposalDto {


    private User user;
    private Board board;
    private String comment;

    public GroupProposal toEntity(){

        return GroupProposal.builder()
                .user(user)
                .board(board)
                .comment(comment)
                .build();

    }


}
