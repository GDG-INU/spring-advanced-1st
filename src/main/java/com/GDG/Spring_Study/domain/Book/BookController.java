package com.GDG.Spring_Study.domain.Book;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {
    /**
     * 도서 등록
     * @param session
     * @return ResponseEntity<?> 대여 성공 또는 실패 메시지
     */
    @PostMapping("/rentBook")
    public ResponseEntity<?> rentBook(HttpSession session) {
        return ResponseEntity.ok().body(null);
    }
}
