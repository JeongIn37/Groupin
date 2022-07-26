package com.songpyeon.groupin.board.controller;

import com.songpyeon.groupin.board.dto.BoardWriteDto;
import com.songpyeon.groupin.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public void writePage(){

    }

    @PostMapping("/write")
    public String writeUpload(BoardWriteDto boardWriteDto){ //User 정보 받아오기 위해 괄호 안에 추가할 것 -> @AuthenticationPrincipal PrincipalDetails principalDetails

        // 서비스 호출
        boardService.save(boardWriteDto);

        // 이미지 업로드 후 돌아갈 주소 설정
        return "글 작성에 성공했다!";

    }
}
