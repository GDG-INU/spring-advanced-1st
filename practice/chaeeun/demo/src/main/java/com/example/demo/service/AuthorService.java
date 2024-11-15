package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // DTO를 받아서 엔티티로 변환 후 저장하고, 저장된 엔티티를 다시 DTO로 변환해서 반환
    public AuthorDTO saveAuthor (AuthorDTO authorDTO) {
        Author author = new Author(authorDTO.getName());
        Author savedAuthor = authorRepository.save(author);
        return new AuthorDTO(savedAuthor.getId(), savedAuthor.getName());
    }

    // 주어진 id로 Author 엔티티를 데이터베이스에서 찾아오고
    // 찾은 Author 엔티티를 AuthorDTO로 변환한다.
    public Optional<AuthorDTO> findById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new AuthorDTO(author.getId(), author.getName()));
    }

    // 데이터베이스에서 모든 Author 엔티티를 가져와서
    // AuthorDTO로 변환하여 반환한다.
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .toList();
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}