package inu.gdsc.library.book;

import inu.gdsc.library.author.Author;
import inu.gdsc.library.author.AuthorRepository;
import inu.gdsc.library.publisher.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;





}
