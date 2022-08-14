package com.songpyeon.groupin.group.domain;

import com.songpyeon.groupin.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder // Board <- BoardWriteDto 넣기 위해 사용
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor  // 빈 생성자
@Data   // Getter & Setter
@Entity // DB에 테이블 생성
public class Groupin {

    @Id // pk 생성
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="group_name")
    private String groupName;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User groupLeader;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @PrePersist // DB에 insert 되기 직전에 실행
    public void created_at(){
        this.createdAt = LocalDateTime.now();
    }
}
