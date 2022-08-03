package com.songpyeon.groupin.repository;

import com.songpyeon.groupin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(String id);
}
