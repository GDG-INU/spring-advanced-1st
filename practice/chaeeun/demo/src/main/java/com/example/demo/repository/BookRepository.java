package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
   // 책을 조회하면 책, 저자, 출판사 정보 가져옴
    @Query("SELECT b FROM Book b JOIN FETCH b.author JOIN FETCH b.publisher WHERE b.id = :id")
    Optional<Book> findBookWithAuthorAndPublisherById(@Param("id") Long id);

    // 특정 단어가 포함된 책 검색(저자,출판사까지 조회)
    @Query("SELECT b FROM Book b JOIN FETCH b.author JOIN FETCH b.publisher WHERE b.title LIKE %:title%")
    List<Book> findByTitleContaining(String title);
}
