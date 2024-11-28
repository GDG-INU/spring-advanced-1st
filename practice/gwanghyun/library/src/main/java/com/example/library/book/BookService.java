package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 책 추가
    public Book addBook(Book book) {
        if (book == null || book.getName() == null) {
            throw new IllegalArgumentException("책 정보가 올바르지 않습니다.");
        }
        return bookRepository.save(book);
    }

    // 책 목록 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 책 ID로 조회
    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    // 책 수정 (불변 객체를 사용하여 새로 생성)
    @Transactional
    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 책을 찾을 수 없습니다. ID: " + bookId));

        // 기존 책 정보를 기반으로 새 객체 생성
        Book newBook = new Book(
                updatedBook.getName(),
                updatedBook.getExplanation(),
                updatedBook.getAuthors(),
                updatedBook.getPublisher()
        );
        newBook.setBookId(existingBook.getBookId()); // 기존 ID 유지

        return bookRepository.save(newBook);
    }

    // 책 삭제
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("삭제할 책을 찾을 수 없습니다. ID: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }
}
