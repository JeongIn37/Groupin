package com.songpyeon.groupin.group.domain;

import com.songpyeon.groupin.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder // Board <- BoardWriteDto 넣기 위해 사용
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor  // 빈 생성자
@Data   // Getter & Setter
@Entity // DB에 테이블 생성
public class GroupRelation {
    @Id // pk 생성
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="group_member")
    @ManyToOne(fetch = FetchType.EAGER)
    private User groupMember;

    @JoinColumn(name="group_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Groupin groupin;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
