package com.example.demo.repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    // 특정 책이 대여중인지 확인
    Optional<Rental> findByBookAndReturnDateIsNull(Book book);
    @Query("SELECT r FROM Rental r JOIN FETCH r.book WHERE r.member.id = :memberId AND r.returnDate IS NULL")
    List<Rental> findByMemberIdAndReturnDateIsNull(Long memberId);
    @Query("SELECT r FROM Rental r JOIN FETCH r.member JOIN FETCH r.book WHERE r.id = :id")
    Optional<Rental> findWithMemberAndBookById(@Param("id") Long RentalId);
}
