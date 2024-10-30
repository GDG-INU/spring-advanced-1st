package com.example.library.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository<Book, Long> {
}
