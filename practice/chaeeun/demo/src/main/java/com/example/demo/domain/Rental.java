package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    // 한 명의 회원이 여러 번 대여 가능
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 특정 책이 대여 중일 경우, 다른 사람에게 동시에 대여가 불가능
    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public static Rental createRental(Member member, Book book) {
        if(member == null || book == null) {
            throw new IllegalArgumentException("Member와 Book은 필수값입니다.");
        }
        return new Rental(null, LocalDate.now(), null, member, book);
    }

    // 책이 반납되었을 때 현재 날짜 출력(반납 날짜 기록)
    public void markAsReturned() {
        this.returnDate = LocalDate.now();
    }
}
