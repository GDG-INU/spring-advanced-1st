package com.example.library.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long rentId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull
    private Member member;

    @Past
    @NotNull
    private LocalDate rentDate;

    private LocalDate returnDate;

    // 기본 생성자 (JPA는 기본 생성자가 필요)
    protected Rent() {}

    // 불변 객체를 위한 생성자
    public Rent(Book book, Member member, LocalDate rentDate, LocalDate returnDate) {
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

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
