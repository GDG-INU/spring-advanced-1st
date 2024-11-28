package com.example.library.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 책 추가
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        logger.info("새로운 책 추가 요청: {}", book);
        Book createdBook = bookService.addBook(book);
        logger.info("책 추가 완료: {}", createdBook);
        return ResponseEntity.ok(createdBook);
    }

    // 책 목록 조회
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("전체 책 목록 조회 요청");
        List<Book> books = bookService.getAllBooks();
        logger.info("조회된 책 개수: {}", books.size());
        return ResponseEntity.ok(books);
    }

    // 책 ID로 조회
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        logger.info("책 조회 요청, ID: {}", bookId);
        Optional<Book> book = bookService.getBookById(bookId);
        if (book.isPresent()) {
            logger.info("책 조회 성공: {}", book.get());
            return ResponseEntity.ok(book.get());
        } else {
            logger.warn("책 조회 실패, ID: {}", bookId);
            return ResponseEntity.notFound().build();
        }
    }

    // 책 수정
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        logger.info("책 수정 요청, ID: {}, 업데이트 데이터: {}", bookId, updatedBook);
        Book book = bookService.updateBook(bookId, updatedBook);
        if (book != null) {
            logger.info("책 수정 성공: {}", book);
            return ResponseEntity.ok(book);
        } else {
            logger.warn("책 수정 실패, ID: {}", bookId);
            return ResponseEntity.notFound().build();
        }
    }

    // 책 삭제
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        logger.info("책 삭제 요청, ID: {}", bookId);
        bookService.deleteBook(bookId);
        logger.info("책 삭제 완료, ID: {}", bookId);
        return ResponseEntity.noContent().build();
    }
}
