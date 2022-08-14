package com.songpyeon.groupin.group.repository;


import com.songpyeon.groupin.group.domain.Groupin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupRepository extends JpaRepository<Groupin, Integer> {
    @Transactional
    @Modifying  // 수정 반영용
    @Query("update User u set u.point = u.point + 10 where u.user_id = :id")
    int groupLeaderPoint(int id);

    @Transactional
    @Modifying  // 수정 반영용
    @Query("update User u set u.point = u.point + 5 where u.user_id = :id")
    int groupMemberPoint(int id);
}
