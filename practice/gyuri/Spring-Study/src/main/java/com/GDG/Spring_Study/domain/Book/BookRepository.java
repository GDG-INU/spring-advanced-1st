package com.GDG.Spring_Study.domain.Book;

import com.GDG.Spring_Study.entitiy.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

//    @Query("SELECT b FROM Book b WHERE " +
//            "(:title IS NULL OR b.title LIKE %:title%) AND " +
//            "(:author IS NULL OR b.author LIKE %:author%) AND " +
//            "(:publisher IS NULL OR b.publisher LIKE %:publisher%) AND " +
//            "(:isbn IS NULL OR b.isbn LIKE %:isbn%) AND " +
//            "(:category IS NULL OR b.category LIKE %:category%)")
//    List<Book> findByFilters(@Param("author") String author,
//                             @Param("publisher") String publisher,
//                             @Param("title") String title,
//                             @Param("isbn") String isbn,
//                             @Param("category") String category);
@Query("SELECT b FROM Book b WHERE " +
        "(:title IS NULL OR b.title LIKE %:title%) AND " +
        "(:author IS NULL OR b.author.author LIKE %:author%) AND " + // 여기서 'author'는 Author 엔티티의 필드
        "(:publisher IS NULL OR b.publisher.publisher_name LIKE %:publisher%) AND " + // 'publisherName' 필드 참조
        "(:isbn IS NULL OR b.isbn LIKE %:isbn%) AND " +
        "(:category IS NULL OR b.category LIKE %:category%)")
List<Book> findByFilters(@Param("author") String author,
                         @Param("publisher") String publisher,
                         @Param("title") String title,
                         @Param("isbn") String isbn,
                         @Param("category") String category);

}
