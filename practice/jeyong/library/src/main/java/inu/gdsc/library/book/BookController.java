package inu.gdsc.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> bookList() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book book(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping
    public List<Book> addBook(@RequestBody Book book){
        bookService.saveBook(book);
        return bookService.getAllBooks();
    }

    @DeleteMapping("/{bookId}")
    public List<Book> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return bookService.getAllBooks();
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book book){
        return bookService.updateBook(bookId, book);
    }

    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.getBooksByAuthor(authorId);
    }

    @GetMapping("/author/{authorName}")
    public List<Book> getBooksByAuthorName(@PathVariable String authorName) {
        return bookService.getBooksByAuthorName(authorName);
    }

    @GetMapping("/publisher/{publisherId}")
    public List<Book> getBooksByPublisher(@PathVariable Long publisherId) {
        return bookService.getBooksByPublisher(publisherId);
    }

    @GetMapping("/publisher/{publisherName}")
    public List<Book> getBooksByPublisherName(@PathVariable String publisherName) {
        return bookService.getBooksByPublisherName(publisherName);
    }

}
