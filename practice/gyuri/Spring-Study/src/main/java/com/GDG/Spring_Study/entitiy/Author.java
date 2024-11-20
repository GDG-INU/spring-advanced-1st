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
@Table(name = "tbl_author")
public class Author extends BaseEntity {
    @Id
    @Comment(value="저자 구분자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Comment(value="저자")
    private String author;
}
