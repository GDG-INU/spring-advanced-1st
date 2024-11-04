package com.example.demo.repository;

import com.example.demo.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(long id);
    Optional<Book> findByTitle(String title);
    List<Book> findAll();
    void deleteById(long id);
}