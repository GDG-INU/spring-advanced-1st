package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class bookService {

    @Autowired
    private bookRepository bookRepository;

    // 책 추가
    public Book addBook(Book book) {
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

    // 책 수정
    public Book updateBook(Long bookId, Book updatedBook) {
        if (bookRepository.existsById(bookId)) {
            updatedBook.setBookId(bookId);
            return bookRepository.save(updatedBook);
        } else {
            return null; // 혹은 예외 처리
        }
    }

    // 책 삭제
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
