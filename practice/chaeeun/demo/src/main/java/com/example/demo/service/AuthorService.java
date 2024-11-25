package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // DTO를 받아서 엔티티로 변환 후 저장하고, 저장된 엔티티를 다시 DTO로 변환해서 반환
    @Transactional
    public AuthorDTO saveAuthor (AuthorDTO authorDTO) {
        // 동일한 이름의 Author가 이미 존재하는지 확인
        if(authorRepository.existsByName(authorDTO.getName())){
            log.error("저자 저장 실패 - 중복된 이름: {}", authorDTO.getName());
            throw new DuplicateEntityException("이미 등록된 저자입니다.");
        }
        Author author = new Author(authorDTO.getName());
        Author savedAuthor = authorRepository.save(author);
        log.info("저자 저장 성공: {}", savedAuthor.getName());
        return new AuthorDTO(savedAuthor.getId(), savedAuthor.getName());
    }

    // 주어진 id로 Author 엔티티를 데이터베이스에서 찾아오고
    // 찾은 Author 엔티티를 AuthorDTO로 변환한다.
    @Transactional(readOnly = true)
    public Optional<AuthorDTO> findById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new AuthorDTO(author.getId(), author.getName()));
    }

    // 데이터베이스에서 모든 Author 엔티티를 가져와서
    // AuthorDTO로 변환하여 반환한다.
    @Transactional(readOnly = true)
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .toList();
    }

    @Transactional
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
        log.info("저자 삭제 성공: id={}", id);
    }
}