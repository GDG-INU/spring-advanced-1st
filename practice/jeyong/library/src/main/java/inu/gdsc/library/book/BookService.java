package inu.gdsc.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void updateBook(Long bookId ,Book update_book) {
        bookRepository.update(bookId, update_book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.delete(bookId);
    }



}
