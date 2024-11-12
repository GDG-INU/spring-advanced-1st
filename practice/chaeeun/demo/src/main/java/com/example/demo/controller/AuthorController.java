package com.example.demo.controller;

import com.example.demo.domain.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping("/{id}")
    public Optional<Author> getAuthor(@PathVariable("id") Long id) {
        return authorService.findById(id);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteById(id);
    }
}
