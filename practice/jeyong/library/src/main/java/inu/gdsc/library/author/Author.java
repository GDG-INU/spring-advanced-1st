package inu.gdsc.library.author;

import inu.gdsc.library.book.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Author {

    @Id @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<Book>();
}
