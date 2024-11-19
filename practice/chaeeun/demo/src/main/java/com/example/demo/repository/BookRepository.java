package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    // 특정 단어가 포함된 책 검색
    @Query("SELECT b FROM Book b JOIN FETCH b.author JOIN FETCH b.publisher WHERE b.title LIKE %:title%")
    List<Book> findByTitleContaining(String title);
}