package inu.gdsc.library.book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private static final Map<Long, Book> store = new HashMap<>();
    private static long sequence = 0L;

    public Book save(Book book) {
        book.setId(++sequence);
        store.put(book.getId(), book);
        return book;
    }

    public Book findById(Long id) {
        return store.get(id);
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long bookId, Book updateParam) {
        Book findBook = findById(bookId);
        findBook.setTitle(updateParam.getTitle());
        findBook.setISBN(updateParam.getISBN());
        findBook.setQuantity(updateParam.getQuantity());
        findBook.setAuthor(updateParam.getAuthor());
    }

    public void delete(Long bookId) {
        store.remove(bookId);
    }
}

