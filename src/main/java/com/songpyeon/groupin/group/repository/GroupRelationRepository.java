package com.songpyeon.groupin.group.repository;

import com.songpyeon.groupin.User.entity.User;
import com.songpyeon.groupin.group.domain.GroupRelation;
import com.songpyeon.groupin.group.domain.Groupin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRelationRepository extends JpaRepository<GroupRelation, Integer> {

    List<GroupRelation> findAllByGroupin_Id(int groupId);
    List<GroupRelation> findAllByGroupMember(User user);


}
