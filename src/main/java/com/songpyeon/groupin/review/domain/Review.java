package com.songpyeon.groupin.review.domain;

import com.songpyeon.groupin.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name="user_id")
    @ManyToOne
    @NotNull
    private User user;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    private int views;

    private int comments;

    private int rating;

    @Column(name="group_image_url")
    private String groupImageUrl;


    private boolean isNotice;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void created_at(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updated_at() { this.updatedAt = LocalDateTime.now(); }


}
