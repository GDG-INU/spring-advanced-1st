package com.example.library.book;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book {

    // 책 번호 (ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long bookId;

    // 책 이름
    @Column(nullable = false)
    private String name;

    // 책 설명
    private String explanation;

    // 대여 정보 (다대일 관계, Book이 Rent의 참조를 가지므로 양방향 관계 설정)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rentList;

    // 생성자, getter, setter, toString() 메소드 등
    public Book() {}

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }
}
