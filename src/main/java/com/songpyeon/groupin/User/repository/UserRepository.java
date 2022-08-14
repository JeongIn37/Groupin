package com.songpyeon.groupin.User.repository;

import com.songpyeon.groupin.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(String id);
}
