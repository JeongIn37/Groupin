package com.songpyeon.groupin.board.service;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.board.dto.BoardWriteDto;
import com.songpyeon.groupin.board.repository.BoardRepository;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.handler.ex.CustomException;
import com.songpyeon.groupin.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    @Value("${file.path}")  //application.yml 파일의 file - path의 값 가져오기
    private String uploadFolder;


    // 카테고리별 글 리스트 불러오기
    public List<Board> listByCategory(@PathVariable String category) {
        // SELECT * FROM Board where category = :category;
        List<Board> boardEntity = boardRepository.findByCategory(category);
        return boardEntity;
    }


    // 글 작성하기
    /*
    * 수정할 것
    *  - 이미지가 업로드 되지 않을 때에도 DB에 UUID가 저장됨
    * */
    public Board savePost(@PathVariable String category, BoardWriteDto boardWriteDto, PrincipalDetails principalDetails){

        UUID uuid = UUID.randomUUID();  //UUID: 파일명과 별개로 고유한 아이디를 만들기 위해 사용하는 규약
        String imageFileName = uuid+"_"+boardWriteDto.getImage_file().getOriginalFilename(); // 업로드하려는 실제 파일명이 들어가는 부분
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        if (boardWriteDto.getImage_file().isEmpty()){
            System.out.println("사진이 업로드 되지 않았습니다.");
            imageFileName = null;
        }
        else{
            try {
                Files.write(imageFilePath, boardWriteDto.getImage_file().getBytes());
                System.out.println(imageFileName + " 업로드 성공!");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        // DB에 저장하기
        // BoardWriteDto에 있는 정보를 Board로 넘겨서 저장하는 과정이 필요
        // Dto에서 toEntity 함수 만들어서 가능
        boardWriteDto.setCategory(category);    // pathvariable로 받아온 category 정보를 Dto에 set
        boardWriteDto.setUser(principalDetails.getUser());
        Board board = boardWriteDto.toEntity(imageFileName);
        return boardRepository.save(board);
    }

    // 글 상세보기
    public Board postDetail(@PathVariable String category, @PathVariable int id){
        Board boardEntity = boardRepository.findByCategoryAndId(category, id);
        if (boardEntity == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }
        boardRepository.updateViewCount(id);
        return boardEntity;
    }

    //글 수정 페이지
    public Board editPage(@PathVariable String category, @PathVariable int id, PrincipalDetails principalDetails) {
        Board boardEntity;
        boardEntity = boardRepository.findByCategoryAndId(category, id);

        if (boardEntity == null) {
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == boardEntity.getUser().getUser_id()) {
            return boardEntity;
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }

    }

    // 글 수정하기
    public Board editPost(@PathVariable String category, @PathVariable int id, Board board, BoardWriteDto boardWriteDto){

        Board post = boardRepository.findByCategoryAndId(category, id);
        if (post == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        post.setTitle(board.getTitle());
        post.setRegion(board.getRegion());
        post.setMax_participants(board.getMax_participants());
        post.setGroup_info(board.getGroup_info());
        post.setRecommend(board.getRecommend());
        post.setGroup_notice(board.getGroup_notice());

        // 이미지 처리
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+boardWriteDto.getImage_file().getOriginalFilename(); // 업로드하려는 실제 파일명이 들어가는 부분
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        if (boardWriteDto.getImage_file().isEmpty()){
            System.out.println("사진이 업로드 되지 않았습니다.");
            post.setGroup_image_url(null);
        } else {
            try {
                Files.write(imageFilePath, boardWriteDto.getImage_file().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            post.setGroup_image_url(imageFileName);
        }

        return boardRepository.save(post);

    }

    // 글 삭제하기
    public void deletePost(@PathVariable String category, @PathVariable int id, PrincipalDetails principalDetails){
        Board post = boardRepository.findByCategoryAndId(category, id);
        if (post == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == post.getUser().getUser_id()) {
            boardRepository.delete(post);
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }
    }

}
