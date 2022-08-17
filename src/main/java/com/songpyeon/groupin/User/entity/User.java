package com.songpyeon.groupin.User.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(nullable=false, unique=true)
    private String id;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String nickname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String region;

    private String role;  //권한
    private String profileImageUrl;  //사진
    private int point;





}
