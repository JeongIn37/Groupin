package com.songpyeon.groupin.review.service;

import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.handler.ex.CustomException;
import com.songpyeon.groupin.handler.ex.ErrorCode;
import com.songpyeon.groupin.review.domain.Review;
import com.songpyeon.groupin.review.dto.ReviewWriteDto;
import com.songpyeon.groupin.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    @Value("${file.path}")
    private String uploadFolder;


    //게시글 리스트 처리
    public List<Review> postList() {
        List<Review> reviewEntity = reviewRepository.findAll();
        return reviewEntity;
    }

    public Page<Review> postsByType(String type, int page){
        PageRequest pageRequest = PageRequest.of(page, 2, Sort.by(Sort.Direction.DESC, type));
        Page<Review> pageEntity = reviewRepository.findAll(pageRequest);
        List<Review> content = pageEntity.getContent();
        return pageEntity;
    }

    public Review postDetail(@PathVariable int id){
        Review reviewEntity = reviewRepository.findById(id).get();
        if (reviewEntity == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }
        reviewRepository.updateViewCount(id);
        return reviewEntity;
    }

    public Review savePost(ReviewWriteDto reviewWriteDto, MultipartFile imageFile, PrincipalDetails principalDetails){

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        if (imageFile.isEmpty()){
            System.out.println("사진이 업로드 되지 않았습니다.");
            imageFileName = null;
        }
        else{
            try {
                Files.write(imageFilePath, imageFile.getBytes());
                System.out.println(imageFileName + " 업로드 성공!");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Review review = reviewWriteDto.toEntity(imageFileName);
        review.setUser(principalDetails.getUser());
        return reviewRepository.save(review);
    }

    public void deletePost(@PathVariable int id, PrincipalDetails principalDetails){
        Review post = reviewRepository.findById(id).get();
        if (post == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == post.getUser().getUser_id()) {
            reviewRepository.delete(post);
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }
    }

    public Review editPage(@PathVariable int id, PrincipalDetails principalDetails) {
        Review reviewEntity;
        reviewEntity = reviewRepository.findById(id).get();

        if (reviewEntity == null) {
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        if (principalDetails.getUser().getUser_id() == reviewEntity.getUser().getUser_id()) {
            return reviewEntity;
        } else {
            throw new CustomException(ErrorCode.NO_AUTHORITY);
        }

    }

    public Review editPost(Review review, MultipartFile imageFile, @PathVariable int id){

        Review post = reviewRepository.findById(id).get();
        if (post == null){
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }

        System.out.println(post);
        post.setTitle(review.getTitle());
        System.out.println(review.getTitle());
        System.out.println(post.getTitle());
        post.setContent(review.getContent());

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageFile.getOriginalFilename(); // 업로드하려는 실제 파일명이 들어가는 부분
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        if (imageFile.isEmpty()){
            System.out.println("사진이 업로드 되지 않았습니다.");
            post.setGroupImageUrl(null);
        } else {
            try {
                Files.write(imageFilePath, imageFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            post.setGroupImageUrl(imageFileName);
        }

        return reviewRepository.save(post);

    }
}
