package com.songpyeon.groupin.group.service;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.domain.Comment;
import com.songpyeon.groupin.board.repository.BoardRepository;
import com.songpyeon.groupin.board.repository.CommentRepository;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.dto.GroupProposalDto;
import com.songpyeon.groupin.group.repository.GroupProposalRepository;
import com.songpyeon.groupin.handler.ex.CustomException;
import com.songpyeon.groupin.handler.ex.ErrorCode;
import com.songpyeon.groupin.user.User;
import com.songpyeon.groupin.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        //Comment comment = commentRepository.findByBoardIdAndId(boardId, id);
        if (groupProposal == null){
            throw new CustomException(ErrorCode.NO_APPLY_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == groupProposal.getUser().getUser_id()) {
            groupProposalRepository.delete(groupProposal);
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }
    }

    public void applyList(){

    }
}
