package com.songpyeon.groupin.board.controller;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.dto.BoardWriteDto;
import com.songpyeon.groupin.board.service.BoardService;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.handler.ex.CustomException;
import com.songpyeon.groupin.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{category}/list")
    public List<Board> postList(@PathVariable String category){
        List<Board> boardEntity = boardService.listByCategory(category);
        return boardEntity;
    }

    @PostMapping("/{category}/write")
    public void write(@PathVariable String category, BoardWriteDto boardWriteDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // 서비스 호출
        boardService.save(category, boardWriteDto, principalDetails);
    }

    @GetMapping("/{category}/{boardId}")
    public Board postDetail(@PathVariable String category, @PathVariable int boardId) {
        Board boardEntity = boardService.detail(category, boardId);
        return boardEntity;
    }
}
