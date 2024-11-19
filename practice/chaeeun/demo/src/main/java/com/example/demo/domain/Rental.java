package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RENTAL_DATE")
    private LocalDate rentalDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    // 한 명의 회원이 여러 번 대여 가능
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 특정 책이 대여 중일 경우, 다른 사람에게 동시에 대여가 불가능
    @OneToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}
