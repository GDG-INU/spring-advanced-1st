package inu.gdsc.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookCount {

    private final BookRepository bookRepository; // Non-static field

    public int countAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.size();
    }
}