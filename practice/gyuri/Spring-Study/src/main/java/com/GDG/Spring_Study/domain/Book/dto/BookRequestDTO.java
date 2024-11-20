package com.GDG.Spring_Study.domain.Book.dto;

import com.GDG.Spring_Study.entitiy.Author;
import com.GDG.Spring_Study.entitiy.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookRequestDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class addBookDTO {
        private String title;
        private Long author;
        private String isbn;
        private Long publisher;
        private String category;
        private String coverImg;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class updateBookDTO {
        private Long id;
        private String title;
        private Long author;
        private String isbn;
        private Long publisher;
        private String category;
        private String coverImg;
    }
}
