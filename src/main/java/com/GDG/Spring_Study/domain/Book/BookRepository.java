package com.GDG.Spring_Study.domain.Book;

import com.GDG.Spring_Study.entitiy.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE " +
            "(:author = '' OR b.author = :author) OR " +
            "(:publisher = '' OR b.publisher = :publisher) OR " +
            "(:title = '' OR b.title LIKE %:title%) OR " +
            "(:isbn = '' OR b.isbn = :isbn) OR " +
            "(:category = '' OR b.category = :category)")
    List<Book> findByFilters(@Param("author") String author,
                             @Param("publisher") String publisher,
                             @Param("title") String title,
                             @Param("isbn") String isbn,
                             @Param("category") String category);
}
