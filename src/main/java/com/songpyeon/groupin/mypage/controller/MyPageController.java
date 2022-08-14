package com.songpyeon.groupin.mypage.controller;

import com.songpyeon.groupin.User.config.auth.PrincipalDetails;
import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.domain.Comment;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/user/myposts")
    public ResponseEntity<?> myPosts(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<Board> myPageEntity = myPageService.myPosts(principalDetails);
        return new ResponseEntity<>(myPageEntity, HttpStatus.OK);
    }

    @GetMapping("/user/mycomments")
    public ResponseEntity<?> myComments(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<Comment> myComments = myPageService.myComments(principalDetails);
        return new ResponseEntity<>(myComments, HttpStatus.OK);
    }

    @GetMapping("/user/myapply")
    public ResponseEntity<?> myApply(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<GroupProposal> proposals = myPageService.myApply(principalDetails);
        return new ResponseEntity<>(proposals, HttpStatus.OK);
    }


}
