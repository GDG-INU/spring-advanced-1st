package com.example.library.service;

import com.example.library.book.Book;
import jakarta.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String name;
    private final String email;
    private final String address;
    private final String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "member_rented_books",  // 대여한 책 목록을 관리하는 테이블
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private final Set<Book> rentedBooks;

    // 기본 생성자 - JPA에서 필요
    protected Member() {
        this.rentedBooks = new HashSet<>();
        this.name = null;
        this.email = null;
        this.address = null;
        this.phoneNumber = null;
    }

    // 불변 객체를 위한 생성자
    public Member(String name, String email, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rentedBooks = new HashSet<>();
    }

    // Getter 메서드만 제공
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Book> getRentedBooks() {
        return Collections.unmodifiableSet(rentedBooks); // 외부에서 직접 수정할 수 없도록 불변 Set으로 반환
    }

    // 대여한 책을 추가하는 메서드
    public void addRentedBook(Book book) {
        rentedBooks.add(book);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rentedBooks=" + rentedBooks +
                '}';
    }
}

