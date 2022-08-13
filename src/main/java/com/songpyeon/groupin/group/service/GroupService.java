package com.songpyeon.groupin.group.service;

import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.group.domain.GroupRelation;
import com.songpyeon.groupin.group.domain.Groupin;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.dto.GroupDto;
import com.songpyeon.groupin.group.dto.GroupRelationDto;
import com.songpyeon.groupin.group.repository.GroupProposalRepository;
import com.songpyeon.groupin.group.repository.GroupRelationRepository;
import com.songpyeon.groupin.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupProposalRepository groupProposalRepository;
    private final GroupRelationRepository groupRelationRepository;

    // 모임 생성 페이지
    public List<GroupProposal> getProposal(int groupId) {
        List<GroupProposal> applyList = groupProposalRepository.findByBoardId(groupId);
        return applyList;
    }

    // 모임 생성
    public Groupin grouping(GroupDto groupDto, PrincipalDetails principalDetails){

        groupDto.setGroupLeader(principalDetails.getUser());
        Groupin groupin = groupDto.toEntity();
        return groupRepository.save(groupin);

    }

    public GroupRelation groupingRelation(GroupRelationDto groupRelationDto){

        //groupRelationDto.setGroupMember();
        //return  groupRelationRepository.save();
        return null;

    }

//    Board board = boardRepository.findById(boardId).orElseThrow(() -> {
//        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
//    });
//    User user = userRepository.findById(userId).orElseThrow(() -> {
//        throw new CustomException(ErrorCode.USER_NOT_FOUND);
//    });
//
//    Comment comment = new Comment();
//        comment.setContent(content);
//        comment.setUser(user);
//        comment.setCategory(category);
//        comment.setBoard(board);
//
//        boardRepository.updateCommentCount(boardId);
//
//        return commentRepository.save(comment);


    // DB에 저장하기
    // BoardWriteDto에 있는 정보를 Board로 넘겨서 저장하는 과정이 필요
//    // Dto에서 toEntity 함수 만들어서 가능
//        boardWriteDto.setCategory(category);    // pathvariable로 받아온 category 정보를 Dto에 set
//        boardWriteDto.setUser(principalDetails.getUser());
//    Board board = boardWriteDto.toEntity(imageFileName);
//        return boardRepository.save(board);


}
