package com.example.library.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long bookId;

    @Column(nullable = false)
    @NotNull
    private String name;

    private String explanation;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rentList;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    // 기본 생성자
    protected Book() {
    }

    // 불변 객체를 위한 생성자
    public Book(String name, String explanation, List<Author> authors, Publisher publisher) {
        this.name = name;
        this.explanation = explanation;
        this.authors = authors;
        this.publisher = publisher;
    }

    // 기존 ID를 유지하기 위한 내부 설정 메서드
    protected void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public List<Rent> getRentList() {
        return rentList;
    }
}
