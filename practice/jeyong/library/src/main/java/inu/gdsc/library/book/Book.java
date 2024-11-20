package inu.gdsc.library.book;

import inu.gdsc.library.author.Author;
import inu.gdsc.library.publisher.Publisher;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity @NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    @Column (name = "book_id")
    private long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private Integer ISBN;

    private Integer quantity;
}
