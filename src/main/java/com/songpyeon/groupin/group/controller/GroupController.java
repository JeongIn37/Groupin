package com.songpyeon.groupin.group.controller;

import com.songpyeon.groupin.User.config.auth.PrincipalDetails;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.domain.GroupRelation;
import com.songpyeon.groupin.group.domain.Groupin;
import com.songpyeon.groupin.group.dto.GroupDto;
import com.songpyeon.groupin.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GroupController {

    private final GroupService groupService;
    // 모임 생성 페이지
    @GetMapping("/grouping/{groupId}")
    public ResponseEntity<?> groupingPage(@PathVariable int groupId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<GroupProposal> proposals = groupService.getProposal(groupId);
        return new ResponseEntity<>(proposals, HttpStatus.OK);
    }

    // 모임 생성하기
    @PostMapping("/grouping/{boardId}")
    public ResponseEntity<?> grouping(@RequestBody GroupDto groupDto, @PathVariable int boardId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Groupin groupEntity = groupService.grouping(groupDto, principalDetails);
        GroupRelation relations = groupService.groupingRelation(boardId, groupEntity, principalDetails);
        return new ResponseEntity<>(groupEntity, HttpStatus.CREATED);
    }

    // 생성된 모임 정보 보기
    @GetMapping("/grouping/members/{groupId}")
    public ResponseEntity<?> groupingMembers(@PathVariable int groupId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<GroupRelation> proposals = groupService.checkMembers(groupId);
        return new ResponseEntity<>(proposals, HttpStatus.OK);
    }


    // 로그인 중인 사용자가 참여 중인 모임 목록 보기
    @GetMapping("/grouping/groups")
    public ResponseEntity<?> groupings(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<GroupRelation> proposals = groupService.checkGroups(principalDetails);
        return new ResponseEntity<>(proposals, HttpStatus.OK);
    }
}
