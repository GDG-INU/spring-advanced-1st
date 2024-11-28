package com.example.library.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long publisherId;

    @Column(nullable = false)
    private String name;

    @NotNull
    private String address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    // 기본 생성자 (JPA는 기본 생성자가 필요)
    protected Publisher() {}

    // Publisher 엔티티를 불변 객체로 만들기 위해 생성자 사용
    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Book> getBooks() {
        return books;
    }
}
