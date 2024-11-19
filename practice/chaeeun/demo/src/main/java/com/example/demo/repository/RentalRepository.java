package com.example.demo.repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    // 특정 책이 대여중인지 확인
    // Optional<Rental> findByBookAndReturnDateIsNull(Book book);
    // 현재 대여 중인 책을 조회 (반납 날짜가 null인 경우)
    List<Rental> findByMemberIdAndReturnDateIsNull(Long memberId);
}
