package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Optional<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}