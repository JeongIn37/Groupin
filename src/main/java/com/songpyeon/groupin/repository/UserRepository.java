package com.songpyeon.groupin.repository;

import com.songpyeon.groupin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    /*
      - 글 작성 (모임 생성 / 후기) - 10pt
      - 댓글 - 1pt
      - 실제 참여 (참여자) - 5pt
      - 그룹장 - 10pt
    */
    @Transactional
    @Modifying  // 수정 반영용
    @Query("update User u set u.point = u.point + 10 where u.user_id = :id")
    int writingPostsPoint(int id);


    @Transactional
    @Modifying  // 수정 반영용
    @Query("update User u set u.point = u.point + 5 where u.user_id = :id")
    int writingCommentsPoint(int id);

    User findById(String id);
}
