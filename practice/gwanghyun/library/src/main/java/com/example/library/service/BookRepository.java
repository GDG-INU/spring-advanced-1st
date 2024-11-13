package com.example.library.service;

import com.example.library.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Fetch Join을 사용하여 Book, Author, Publisher를 한 번에 조회
    @Query("SELECT b FROM Book b JOIN FETCH b.authors a JOIN FETCH b.publisher p WHERE b.id = :bookId")
    Book findBookWithAuthorsAndPublisher(@Param("bookId") Long bookId);
}
