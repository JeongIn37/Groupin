package com.songpyeon.groupin.group.controller;

import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.dto.GroupProposalDto;
import com.songpyeon.groupin.group.service.GroupProposalService;
import com.songpyeon.groupin.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GroupProposalController {

    private final GroupProposalService groupProposalService;

    @PostMapping("/{boardId}/apply")
    public ResponseEntity<?> apply(GroupProposalDto groupProposalDto, @PathVariable int boardId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        GroupProposal group = groupProposalService.apply(groupProposalDto.getComment(), boardId, principalDetails.getUser().getUser_id());
        //Comment comment = commentService.writeComment(commentDto.getContent(), category, boardId, principalDetails.getUser().getUser_id());
        return new ResponseEntity<>(new CMRespDto<>(1, "그룹 신청 완료", group), HttpStatus.CREATED);
    }

    @DeleteMapping("/{boardId}/{proposalId}/cancel")
    public ResponseEntity<?> cancelApply(@PathVariable int boardId, @PathVariable int proposalId,  @AuthenticationPrincipal PrincipalDetails principalDetails){
        groupProposalService.cancelApply(boardId, proposalId, principalDetails);
        Map<String, String> map = new HashMap<>();
        map.put("result", "그룹 신청 취소");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
