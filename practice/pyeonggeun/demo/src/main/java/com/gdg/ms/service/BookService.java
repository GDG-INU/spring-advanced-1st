package com.gdg.ms.service;

import com.gdg.ms.entity.Book;
import com.gdg.ms.mapper.BookSqlMapper;
import com.gdg.ms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // 도서 리스트
    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    // 도서 등록
    public void registerBook(Book book) {
        book.setIs_check_out("N");
        book.setCreated_at(LocalDateTime.now());
        bookRepository.save(book);
    }

    // 도서 삭제
    public void deleteBook(int bookPk) {
        bookRepository.deleteById(bookPk);
    }

}
