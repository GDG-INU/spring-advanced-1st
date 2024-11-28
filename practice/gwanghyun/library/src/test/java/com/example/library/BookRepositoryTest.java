package com.example.library;

import com.example.library.book.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFindBook() {
        // Given
        Book book = new Book("Test Book", "Description", null, null);
        Book savedBook = bookRepository.save(book);

        // When
        Optional<Book> foundBook = bookRepository.findById(savedBook.getBookId());

        // Then
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getName()).isEqualTo("Test Book");
    }

    @Test
    void testFindAllBooks() {
        // Given
        bookRepository.save(new Book("Book 1", "Description 1", null, null));
        bookRepository.save(new Book("Book 2", "Description 2", null, null));

        // When
        List<Book> books = bookRepository.findAll();

        // Then
        assertThat(books).hasSize(2);
    }

    @Test
    void testDeleteBook() {
        // Given
        Book book = new Book("Book to Delete", "Description", null, null);
        Book savedBook = bookRepository.save(book);

        // When
        bookRepository.deleteById(savedBook.getBookId());
        Optional<Book> foundBook = bookRepository.findById(savedBook.getBookId());

        // Then
        assertThat(foundBook).isEmpty();
    }
}
