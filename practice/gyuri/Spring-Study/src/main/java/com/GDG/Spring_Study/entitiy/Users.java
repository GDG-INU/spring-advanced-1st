package com.GDG.Spring_Study.entitiy;

import com.GDG.Spring_Study.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity {
    @Id
    @Comment(value="사용자 정보 구분자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Comment(value="이름")
    private String name;
    @Column
    @Comment(value="이메일")
    private String email;
    @Column
    @Comment(value="비밀번호")
    private String passwd;
    @Column
    @Comment(value="학과")
    private String major;
}