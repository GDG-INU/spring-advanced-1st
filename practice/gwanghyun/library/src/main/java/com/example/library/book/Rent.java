package com.example.library.book;

import jakarta.persistence.*;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String rentDate;
    private String returnDate;

    // Rent 엔티티를 불변 객체로 만들기 위해 생성자 사용
    public Rent(Book book, Member member, String rentDate, String returnDate) {
        this.book = book;
        this.member = member;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public Long getRentId() {
        return rentId;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public String getRentDate() {
        return rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
