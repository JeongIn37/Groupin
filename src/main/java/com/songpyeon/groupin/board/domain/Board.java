package com.songpyeon.groupin.board.domain;

import com.songpyeon.groupin.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공)

@Builder // User <- SignupDto 넣기 위해 사용
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor  // 빈 생성자
@Data   // Getter & Setter
@Entity // DB에 테이블 생성
public class Board {

    @Id // pk 생성
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 DB를 따라감 (mysql-AutoIncrement, oracle-sequence )
    private int id;

    @Column(name="category")
    @NotNull
    private String category;

    @JoinColumn(name="user_id")
    @ManyToOne
    @NotNull
    private User user;    // user와 board는 1:N

    private boolean is_notice;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @Column(nullable = false, length = 50)
    private String title;
    private int views;
    private int comments;
    private String region;
    private String max_participants;
    @Column(nullable = false)
    private String group_info;
    private String recommend;
    private String group_notice;
    private String group_image_url; // 사진을 전송 받아서 사진을 서버 특정 폴더에 저장 - DB에 그 저장된 경로를 insert


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrePersist // DB에 insert 되기 직전에 실행
    public void created_at(){
        this.created_at = LocalDateTime.now();
    }

    @PreUpdate  // update 되기 직전 실행
    public void updated_at() { this.updated_at = LocalDateTime.now(); }


}
