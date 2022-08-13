package com.songpyeon.groupin.group.service;

import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.group.domain.GroupRelation;
import com.songpyeon.groupin.group.domain.Groupin;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.dto.GroupDto;
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


    // Relation 생성
    public GroupRelation groupingRelation(int boardId, Groupin groupin, PrincipalDetails principalDetails){

        // status가 승인 완료인 user 정보 가져오기
        List<GroupProposal> applyList = groupProposalRepository.findAllByBoardIdAndStatus(boardId, "승인 완료");

        // 그룹 리더도 relation을 갖기 위해
        GroupRelation groupleader = new GroupRelation();
        groupleader.setGroupMember(principalDetails.getUser());
        groupleader.setGroupin(groupin);

        // 각 User 정보를 Group 정보랑 같이 테이블에 저장하기
        for (GroupProposal groupProposal : applyList){
            GroupRelation groupRelation = new GroupRelation();
            groupRelation.setGroupMember(groupProposal.getUser());
            groupRelation.setGroupin(groupin);
            groupRelationRepository.save(groupRelation);
        }

        return null;

    }


    // 모임별 참가자 리스트
    public List<GroupRelation> checkMembers(int groupId) {
        List<GroupRelation> lst = groupRelationRepository.findAllByGroupin_Id(groupId);
        return lst;
    }

    // 유저별 모임 리스트
    public List<GroupRelation> checkGroups(PrincipalDetails principalDetails) {
        List<GroupRelation> lst = groupRelationRepository.findAllByGroupMember(principalDetails.getUser());
        return lst;
    }

}
