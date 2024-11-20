package inu.gdsc.library.author;

import inu.gdsc.library.book.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<Book>();
}
