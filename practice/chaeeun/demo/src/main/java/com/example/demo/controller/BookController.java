package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookId(@PathVariable("id") Long id){
        return bookService.findById(id);
    }

    @GetMapping("/title")
    public Optional<Book> getBookTitle(@RequestParam String title){
        return bookService.findByTitle(title);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteById(id);
        return id + "번 도서가 삭제되었습니다.";
    }

}