package com.example.library.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "책 이름은 필수입니다.")
    private String name;

    @Column(length = 1000)
    private String explanation;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Rent> rentList;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    // 기본 생성자 (JPA 요구사항)
    protected Book() {
    }

    // 불변 객체를 위한 생성자
    public Book(String name, String explanation, List<Author> authors, Publisher publisher) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("책 이름은 필수입니다.");
        }
        if (publisher == null) {
            throw new IllegalArgumentException("출판사는 필수입니다.");
        }

        this.name = name;
        this.explanation = explanation;
        this.authors = authors;
        this.publisher = publisher;
    }

    // Setter는 필요 최소한으로 유지
    protected void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    // Getter 메서드
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
