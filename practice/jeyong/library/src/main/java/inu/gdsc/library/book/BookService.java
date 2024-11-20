package inu.gdsc.library.book;

import inu.gdsc.library.author.AuthorRepository;
import inu.gdsc.library.publisher.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks () {
        return bookRepository.findAll();
    }

    public Book getBook(long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("책을 찾을 수 없습니다."));
    }

    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book updateBook(Long bookId, Book updateBook) {
        Book originalBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("책을 찾을 수 없습니다."));

        originalBook.setTitle(updateBook.getTitle());
        originalBook.setISBN(updateBook.getISBN());
        originalBook.setAuthor(updateBook.getAuthor());
        originalBook.setQuantity(updateBook.getQuantity());
        originalBook.setPublisher(updateBook.getPublisher());
        return originalBook;
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

    public List<Book> getBooksByPublisher(Long publisherId) {
        return bookRepository.findByPublisherId(publisherId);
    }

    public List<Book> getBooksByPublisherName(String publisherName) {
        return bookRepository.findByPublisherName(publisherName);
    }
}
