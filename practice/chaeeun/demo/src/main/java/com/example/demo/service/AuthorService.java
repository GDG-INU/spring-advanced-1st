package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
