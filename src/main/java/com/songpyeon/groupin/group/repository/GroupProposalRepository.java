package com.songpyeon.groupin.group.repository;

import com.songpyeon.groupin.board.domain.Comment;
import com.songpyeon.groupin.group.domain.GroupProposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupProposalRepository extends JpaRepository<GroupProposal, Integer> {

    GroupProposal findByBoardIdAndId(int boardId, int gCommentId);

}
