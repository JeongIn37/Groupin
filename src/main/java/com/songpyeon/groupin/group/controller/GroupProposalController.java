package com.songpyeon.groupin.group.controller;

import com.songpyeon.groupin.User.config.auth.PrincipalDetails;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.dto.GroupProposalDto;
import com.songpyeon.groupin.group.service.GroupProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GroupProposalController {

    private final GroupProposalService groupProposalService;

    @PostMapping("/{boardId}/apply")
    public ResponseEntity<?> apply(@RequestBody GroupProposalDto groupProposalDto, @PathVariable int boardId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        GroupProposal group = groupProposalService.apply(groupProposalDto.getComment(), boardId, principalDetails.getUser().getUser_id());
        //Comment comment = commentService.writeComment(commentDto.getContent(), category, boardId, principalDetails.getUser().getUser_id());
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @DeleteMapping("/{boardId}/{proposalId}/cancel")
    public ResponseEntity<?> cancelApply(@PathVariable int boardId, @PathVariable int proposalId,  @AuthenticationPrincipal PrincipalDetails principalDetails){
        groupProposalService.cancelApply(boardId, proposalId, principalDetails);
        Map<String, String> map = new HashMap<>();
        map.put("result", "그룹 신청 취소");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 신청 승인
    @PatchMapping("/admitApply/{proposalId}")
    public ResponseEntity<Object> admitApply(@PathVariable int proposalId){
        GroupProposal applyEntity = groupProposalService.admitApply(proposalId);
        return new ResponseEntity<>(applyEntity, HttpStatus.OK);
    }

    // 신청 거절
    @PatchMapping("/rejectApply/{proposalId}")
    public ResponseEntity<Object> rejectApply(@PathVariable int proposalId){
        GroupProposal applyEntity = groupProposalService.rejectApply(proposalId);
        return new ResponseEntity<>(applyEntity, HttpStatus.OK);
    }

    // 신청 보류
    @PatchMapping("/waitingStatus/{proposalId}")
    public ResponseEntity<Object> waitingStatus(@PathVariable int proposalId){
        GroupProposal applyEntity = groupProposalService.waiting(proposalId);
        return new ResponseEntity<>(applyEntity, HttpStatus.OK);
    }


}
