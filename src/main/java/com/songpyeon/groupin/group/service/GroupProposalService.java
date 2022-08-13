package com.songpyeon.groupin.group.service;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.repository.BoardRepository;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.repository.GroupProposalRepository;
import com.songpyeon.groupin.handler.ex.CustomException;
import com.songpyeon.groupin.handler.ex.ErrorCode;
import com.songpyeon.groupin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupProposalService {

    private final GroupProposalRepository groupProposalRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public GroupProposal apply(String comment, int boardId, int userId){

        Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        });
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });

        GroupProposal groupProposal = new GroupProposal();
        groupProposal.setUser(user);
        groupProposal.setBoard(board);

        groupProposal.setComment(comment);
        return groupProposalRepository.save(groupProposal);

    }



    public void cancelApply(int boardId, int proposalId, PrincipalDetails principalDetails){
        GroupProposal groupProposal = groupProposalRepository.findByBoardIdAndId(boardId, proposalId);
        if (groupProposal == null){
            throw new CustomException(ErrorCode.NO_APPLY_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == groupProposal.getUser().getUser_id()) {
            groupProposalRepository.delete(groupProposal);
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }
    }

    public GroupProposal admitApply(int proposalId){
        GroupProposal applyUser = groupProposalRepository.findById(proposalId);
        applyUser.setStatus("승인 완료");
        return groupProposalRepository.save(applyUser);
    }

    public GroupProposal waiting(int proposalId){
        GroupProposal gp = groupProposalRepository.findById(proposalId);
        gp.setStatus("승인 대기 중");
        return groupProposalRepository.save(gp);
    }

    public GroupProposal rejectApply(int proposalId){
        GroupProposal gp = groupProposalRepository.findById(proposalId);
        gp.setStatus("승인 거절");
        return groupProposalRepository.save(gp);
    }

}
