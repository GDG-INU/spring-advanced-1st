package com.GDG.Spring_Study.domain.Book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookRequestDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class searchBookDTO {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private String publisher;
        private String category;
    }
}
