package com.songpyeon.groupin.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.songpyeon.groupin.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor  // 빈 생성자
@Data   // Getter & Setter
@Entity // DB에 테이블 생성
public class Comment {

    @Id // pk 생성
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 DB를 따라감 (mysql-AutoIncrement, oracle-sequence )
    private int id;

    //@JsonIgnoreProperties({"nickname"})  // response에 불필요한 정보 들고 오지 않도록 설정 가
    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name="board_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Board board;

    private String category;

    @Column(length = 200, nullable = false)
    private String content;
    @Column(name="created_at")
    private LocalDateTime createdAt;




    @PrePersist // DB에 insert 되기 직전에 실행
    public void created_at(){
        this.createdAt = LocalDateTime.now();
    }

}
