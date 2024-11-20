package inu.gdsc.library.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BookRepositoryTest {

    BookRepository bookRepository = new BookRepository();

    @Test
    void saveBook() {
        Book book = new Book("Test", "A", 1234, 10);

        Book savedBook = bookRepository.save(book);

        Book findBook = bookRepository.findById(book.getId());
        Assertions.assertThat(findBook).isEqualTo(savedBook);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book("Test", "A", 1234, 10);
        Book book2 = new Book("Test2", "B", 12345, 10);
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> result = bookRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(book1, book2);
    }

    @Test
    void getBook() {
        Book book = new Book("Test", "A", 1234, 10);

        bookRepository.save(book);
        Book getBook = bookRepository.findById(book.getId());

        Assertions.assertThat(getBook).isEqualTo(book);
    }


    @Test
    void updateBook() {

        Book book = new Book("Test", "A", 1234, 10);

        Book savedBook = bookRepository.save(book);

        Book update_book = new Book("Update", "B", 12345, 100);
        bookRepository.update(savedBook.getId(), update_book);

        Assertions.assertThat(update_book.getTitle()).isEqualTo(bookRepository.findById(savedBook.getId()).getTitle());
        Assertions.assertThat(update_book.getAuthor()).isEqualTo(bookRepository.findById(savedBook.getId()).getAuthor());
        Assertions.assertThat(update_book.getISBN()).isEqualTo(bookRepository.findById(savedBook.getId()).getISBN());
        Assertions.assertThat(update_book.getQuantity()).isEqualTo(bookRepository.findById(savedBook.getId()).getQuantity());
    }

    @Test
    void deleteBook() {
        Book book = new Book("Test", "A", 1234, 10);
        bookRepository.save(book);

        int not_delete = bookRepository.findAll().size();

        bookRepository.delete(book.getId());

        int delete = bookRepository.findAll().size();

        Assertions.assertThat(delete).isNotEqualTo(not_delete);
    }
}