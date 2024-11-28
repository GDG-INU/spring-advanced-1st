package com.example.library;

import com.example.library.book.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        // Given
        Book book = new Book("New Book", "Description", null, null);
        when(bookRepository.save(book)).thenReturn(book);

        // When
        Book savedBook = bookService.addBook(book);

        // Then
        assertThat(savedBook).isEqualTo(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllBooks() {
        // Given
        List<Book> books = List.of(new Book("Book 1", "Description 1", null, null));
        when(bookRepository.findAll()).thenReturn(books);

        // When
        List<Book> result = bookService.getAllBooks();

        // Then
        assertThat(result).isEqualTo(books);
    }

    @Test
    void testUpdateBookThrowsExceptionIfNotFound() {
        // Given
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Then
        assertThrows(IllegalArgumentException.class, () -> bookService.updateBook(bookId, new Book("Updated", "Description", null, null)));
    }

    @Test
    void testDeleteBook() {
        // Given
        Long bookId = 1L;
        when(bookRepository.existsById(bookId)).thenReturn(true);

        // When
        bookService.deleteBook(bookId);

        // Then
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
