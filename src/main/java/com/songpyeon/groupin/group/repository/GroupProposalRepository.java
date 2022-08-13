package com.songpyeon.groupin.group.repository;

import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.group.domain.GroupProposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupProposalRepository extends JpaRepository<GroupProposal, Integer> {

    GroupProposal findById(int id);
    GroupProposal findByBoardIdAndId(int boardId, int gCommentId);

    List<GroupProposal> findByUser(User user);

    List<GroupProposal> findByBoardId(int boardId);

    List<GroupProposal> findAllByBoardIdAndStatus(int boardId, String status);

}
