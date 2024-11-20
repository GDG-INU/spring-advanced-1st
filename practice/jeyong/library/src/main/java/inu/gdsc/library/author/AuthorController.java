package inu.gdsc.library.author;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> authors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public Author author(@PathVariable Long authorId){
        return authorService.getAuthor(authorId);
    }

    @PostMapping
    public List<Author> addAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PutMapping("/{authorId}")
    public Author updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        return authorService.updateAuthor(authorId, author);
    }

    @DeleteMapping("/{authorId}")
    public List<Author> deleteAuthor(@PathVariable Long authorId) {
        return authorService.deleteAuthor(authorId);
    }

}
