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
        if (bookRepository.existsById(bookId)) {
            // 수정된 book 정보를 바탕으로 새로운 객체 생성
            Book newBook = new Book(
                    updatedBook.getName(),
                    updatedBook.getExplanation(),
                    updatedBook.getAuthors(),
                    updatedBook.getPublisher()
            );
            return bookRepository.save(newBook);
        } else {
            return null; // 책이 없을 경우 예외를 던질 수도 있습니다.
        }
    }

    // 책 삭제
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
