package com.songpyeon.groupin.group.repository;


import com.songpyeon.groupin.group.domain.Groupin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groupin, Integer> {

}
