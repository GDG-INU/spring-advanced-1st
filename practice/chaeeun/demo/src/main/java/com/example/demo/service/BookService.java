package com.example.demo.service;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.dto.BookDTO;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public BookDTO saveBook(BookDTO bookDTO) {
        // ID를 통해 Author와 Publisher를 데이터베이스에서 조회
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> {
                    log.error("존재하지 않는 저자입니다: authorId={}", bookDTO.getAuthorId());
                    return new IllegalArgumentException("Author not found with ID: " + bookDTO.getAuthorId());

                });
        Publisher publisher = publisherRepository.findById(bookDTO.getPublisherId())
                .orElseThrow(() -> {
                    log.error("존재하지 않는 출판사입니다: publisherId={}", bookDTO.getPublisherId());
                    return new IllegalArgumentException("Publisher not found with ID: " + bookDTO.getPublisherId());
                });

        // Book 객체 생성 및 저장
        Book book = new Book(bookDTO.getTitle(), author, publisher);
        Book savedBook = bookRepository.save(book);

        log.info("도서 저장에 성공하였습니다. title={}, author={}, publisher={}", bookDTO.getTitle(), author, publisher);

        author.getBookList().add(book);
        publisher.getBookList().add(book);

        return new BookDTO(savedBook);
    }

    public Optional<BookDTO> findById(Long id) {
        return bookRepository.findBookWithAuthorAndPublisherById(id)
                .map(book -> {
                    log.info("도서 조회에 성공하였습니다: {}", id);
                return new BookDTO(
                            book.getId(),
                            book.getTitle(),
                            book.getAuthor().getName(),
                            book.getPublisher().getName(),
                            book.getAuthor().getId(),
                            book.getPublisher().getId()
                    );
                });
    }

    public List<BookDTO> findByTitle(String title){
        return bookRepository.findByTitleContaining(title).stream()
                .map(book -> new BookDTO(book))
                .collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(book -> new BookDTO(book))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
        log.info("도서 삭제를 완료했습니다. id={}", id);
    }
}