package com.songpyeon.groupin.board.controller;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.dto.BoardWriteDto;
import com.songpyeon.groupin.board.service.BoardService;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{category}/list")
    public ResponseEntity<List<Board>> postList(@PathVariable String category){
        List<Board> boardEntity = boardService.postList(category);
        return new ResponseEntity<>(boardEntity, HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<Page<Board>> postsByType(@PathVariable String category, @RequestParam String type, @RequestParam int page){
        Page<Board> pageEntity = boardService.postsByType(category, type, page);
        return new ResponseEntity<>(pageEntity, HttpStatus.OK);
    }

    @GetMapping("/{category}/{boardId}")
    public ResponseEntity<Board> postDetail(@PathVariable String category, @PathVariable int boardId) {
        Board boardEntity = boardService.postDetail(category, boardId);
        return new ResponseEntity<>(boardEntity, HttpStatus.OK);
        //return boardEntity;
    }

    @PostMapping("/{category}/write")
    public ResponseEntity<Object> writePost(@PathVariable String category,
                                            @RequestPart MultipartFile imageFile,
                                            @RequestPart BoardWriteDto request,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails){
        // 서비스 호출
        Board boardEntity = boardService.savePost(category, imageFile, request, principalDetails);
        return new ResponseEntity<>(boardEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{category}/{boardId}/edit")
    public ResponseEntity<Object> editPost(@PathVariable String category, @PathVariable int boardId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Board boardEntity = boardService.editPage(category, boardId, principalDetails);
        return new ResponseEntity<>(boardEntity, HttpStatus.OK);
    }

    @PatchMapping("/{category}/{boardId}/edit")
    public ResponseEntity<Object> editPost(@PathVariable String category,
                                           @RequestPart MultipartFile imageFile,
                                           @PathVariable int boardId, @RequestPart Board request){
        Board boardEntity = boardService.editPost(category, imageFile, boardId, request);
        return new ResponseEntity<>(boardEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{category}/{boardId}/delete")
    public ResponseEntity<Object> deletePost(@PathVariable String category, @PathVariable int boardId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        //User userEntity = (User) principalDetailsService.loadUserByUsername(principalDetails.getUsername());
        boardService.deletePost(category, boardId, principalDetails);
        Map<String, String> map = new HashMap<>();
        map.put("result", "삭제 완료");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Board>> search(@RequestParam String query){
        List<Board> posts = boardService.search(query);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }



}
