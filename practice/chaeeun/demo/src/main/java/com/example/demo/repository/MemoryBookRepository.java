package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemoryBookRepository implements BookRepository {

    private static Map<Long, Book> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Book save(Book book) {
        book.setId(++sequence);
        store.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return store.values().stream()
                .filter(member ->member.getTitle().equals(title))
                .findAny();
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(long id){
        store.remove(id);
    }
}