package com.example.demo.dto;

import com.example.demo.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String authorName;
    private String publisherName;
    private Long authorId;
    private Long publisherId;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.authorName = book.getAuthor().getName();
        this.publisherName = book.getPublisher().getName();
        this.authorId = book.getAuthor().getId();
        this.publisherId = book.getPublisher().getId();
    }
}