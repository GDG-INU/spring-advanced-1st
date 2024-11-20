package inu.gdsc.library.publisher;

import inu.gdsc.library.author.Author;
import inu.gdsc.library.book.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Publisher {

    @Id @GeneratedValue
    @Column (name = "publisher_id")
    private long id;

    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<Book>();
}
