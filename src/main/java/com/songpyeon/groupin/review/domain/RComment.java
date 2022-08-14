package com.songpyeon.groupin.review.domain;

import com.songpyeon.groupin.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RComment {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name="review_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Review review;

    @Column(length = 200, nullable = false)
    private String content;
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void created_at(){
        this.createdAt = LocalDateTime.now();
    }
}
