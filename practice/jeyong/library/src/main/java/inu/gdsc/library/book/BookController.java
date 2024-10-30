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
    public List<Book> deleteBook(@PathVariable Long bookId, @RequestBody Book book){
        bookService.deleteBook(bookId);
        return bookService.getAllBooks();
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book book){
        bookService.updateBook(bookId, book);
        return bookService.getBook(bookId);
    }


}
