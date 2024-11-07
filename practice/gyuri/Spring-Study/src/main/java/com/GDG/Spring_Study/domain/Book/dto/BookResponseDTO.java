package com.GDG.Spring_Study.domain.Book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookResponseDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class searchBookDTO {
        private String title;
        private String author;
        private String isbn;
        private String publisher;
        private String category;
        private String coverImg;
    }
}
