package inu.gdsc.library.book;

import lombok.Data;

@Data
public class Book {
    private long id;
    private String title;
    private String author;
    private Integer ISBN;
    private Integer quantity;

    public Book() {
    }

    public Book(String title, String author, Integer ISBN, Integer quantity) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }
}
