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

    public BookDTO(Long id, String title, String authorName, String publisherName, Long authorId, Long publisherId) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.authorId = authorId;
        this.publisherId = publisherId;
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