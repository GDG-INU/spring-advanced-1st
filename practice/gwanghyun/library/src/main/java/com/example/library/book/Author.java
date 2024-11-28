package com.example.library.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long authorId;

    @Column(nullable = false)
    @NotNull
    private String name;

  

    @Column(nullable = false)
    private String biography;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    // 기본 생성자 (JPA는 기본 생성자가 필요)
    protected Author() {}

    // Author 엔티티를 불변 객체로 만들기 위해 생성자 사용
    public Author(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }

    public List<Book> getBooks() {
        return books;
    }
}
