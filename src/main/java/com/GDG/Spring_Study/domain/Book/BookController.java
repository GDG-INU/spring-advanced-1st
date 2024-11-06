package com.GDG.Spring_Study.domain.Book;

import com.GDG.Spring_Study.domain.Book.dto.BookRequestDTO;
import com.GDG.Spring_Study.domain.Book.service.BookServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookServiceImpl bookService;

    /**
     * 도서 검색
     * @param author
     * @param publisher
     * @param title
     * @param isbn
     * @param category
     * @return ResponseEntity<?> 대여 성공 또는 실패 메시지
     */
    @GetMapping("/searchBook")
    public ResponseEntity<?> searchBook(@RequestParam(value = "author", defaultValue = "") String author,
                                        @RequestParam(value = "publisher", defaultValue = "") String publisher,
                                        @RequestParam(value = "title", defaultValue = "") String title,
                                        @RequestParam(value = "isbn", defaultValue = "") String isbn,
                                        @RequestParam(value = "category", defaultValue = "") String category) {
        return ResponseEntity.ok().body(bookService.searchBook(author, publisher, title, isbn, category));
    }

    /**
     * 도서 등록
     * @param addBookDTO
     * @return ResponseEntity<?>
     */
    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody BookRequestDTO.addBookDTO addBookDTO) {
        return ResponseEntity.ok().body(bookService.addBook(addBookDTO));
    }
}
