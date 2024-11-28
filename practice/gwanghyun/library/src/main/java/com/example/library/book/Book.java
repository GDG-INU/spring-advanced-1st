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

    // 기본 생성자 (JPA는 기본 생성자가 필요)
    protected Book() {}

    // Book 엔티티를 불변 객체로 만들기 위해 생성자 사용
    public Book(String name, String explanation, List<Author> authors, Publisher publisher) {
        this.name = name;
        this.explanation = explanation;
        this.authors = authors;
        this.publisher = publisher;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
