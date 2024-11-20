package com.GDG.Spring_Study.domain.Author;

import com.GDG.Spring_Study.entitiy.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
