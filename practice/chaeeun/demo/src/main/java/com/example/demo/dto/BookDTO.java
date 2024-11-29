package com.example.demo.dto;
import com.example.demo.domain.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookDTO {
    private Long id;

    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    private String title;
    private String authorName;
    private String publisherName;
    @NotNull(message = "저자의 ID는 필수입니다.")
    private Long authorId;
    @NotNull(message = "출판사의 ID는 필수입니다.")
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