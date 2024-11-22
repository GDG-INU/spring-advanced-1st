package com.example.demo.repository;

import com.example.demo.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, Long> {
    // 이름으로 저자의 존재여부를 확인
    boolean existsByName(String name);
}
