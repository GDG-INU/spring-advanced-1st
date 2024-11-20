package inu.gdsc.library.author;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("작가를 찾을 수 없습니다."));
    }

    public List<Author> saveAuthor(Author author) {
        authorRepository.save(author);
        return authorRepository.findAll();
    }

    public List<Author> deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
        return authorRepository.findAll();
    }

    public Author updateAuthor(Long authorId, Author updateAuthor){
        Author originalAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("작가를 찾을 수 없습니다."));
        originalAuthor.setName(updateAuthor.getName());
        originalAuthor.setBooks(updateAuthor.getBooks());
        return originalAuthor;
    }

}
