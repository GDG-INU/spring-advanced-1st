package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public BookDTO createBook(@Valid @RequestBody BookDTO bookDTO){
        return bookService.saveBook(bookDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookId(@PathVariable("id") Long id){
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> getBookTitle(@RequestParam String title){
        List<BookDTO> books = bookService.findByTitle(title);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){
        bookService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 not content
    }
}