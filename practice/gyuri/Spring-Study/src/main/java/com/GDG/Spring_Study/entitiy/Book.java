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
@Table(name = "tbl_book")
public class Book extends BaseEntity {
    @Id
    @Comment(value="도서 정보 구분자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Comment(value="제목")
    private String title;
    @Column
    @Comment(value="ISBN")
    private String isbn;
    @Column
    @Comment(value="카테고리")
    private String category;
    @Column
    @Comment(value="커버 이미지")
    private String coverImg;

    @ManyToOne
    @Comment(value = "저자 구분자")
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @Comment(value = "출판사 구분자")
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;
}
