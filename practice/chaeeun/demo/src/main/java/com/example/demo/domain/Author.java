package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // 중복 방지를 위해 unique 추가
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

    public void changeAuthorName(String name) {
        this.name = name;
    }
}
