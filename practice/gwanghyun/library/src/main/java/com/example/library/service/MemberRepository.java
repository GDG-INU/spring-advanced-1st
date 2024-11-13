package com.example.library.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Fetch join을 사용하여 대여한 책을 함께 조회
    @Query("SELECT m FROM Member m JOIN FETCH m.rentedBooks WHERE m.id = :memberId")
    Member findByIdWithRentedBooks(Long memberId);
}
