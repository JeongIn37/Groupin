package com.songpyeon.groupin.mypage.service;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.domain.Comment;
import com.songpyeon.groupin.board.repository.BoardRepository;
import com.songpyeon.groupin.board.repository.CommentRepository;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.group.domain.GroupProposal;
import com.songpyeon.groupin.group.repository.GroupProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final GroupProposalRepository groupProposalRepository;

    // 내가 작성한 글 불러오기
    public List<Board> myPosts(PrincipalDetails principalDetails) {
        User loginUser = principalDetails.getUser();
        List<Board> boardEntity = boardRepository.findByUser(loginUser);
//        System.out.println("board by User : " + boardEntity);
        return boardEntity;
    }

    // 내가 쓴 댓글 불러오기
    public List<Comment> myComments(PrincipalDetails principalDetails) {
        User loginUser = principalDetails.getUser();
        List<Comment> myComments = commentRepository.findByUser(loginUser);
        return myComments;
    }


    // 내가 신청한 글 목록
    public List<GroupProposal> myApply(PrincipalDetails principalDetails) {
        User loginUser = principalDetails.getUser();
        List<GroupProposal> proposals = groupProposalRepository.findByUser(loginUser);
        return proposals;
    }




}
