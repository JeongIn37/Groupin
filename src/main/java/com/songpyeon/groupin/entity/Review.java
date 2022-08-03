package com.songpyeon.groupin.entity;

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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    private String user_id;

    private int views;

    private int comments;

    private int rating;


    private boolean is_notice;

    private String created_at;

    private String updated_at;


}
