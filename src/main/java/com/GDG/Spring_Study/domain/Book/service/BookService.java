package com.GDG.Spring_Study.domain.Book.service;

import com.GDG.Spring_Study.domain.Book.dto.BookRequestDTO;
import com.GDG.Spring_Study.global.response.ApiResponse;

public interface BookService {
    ApiResponse<?> searchBook(String author,String  publisher,String  title,String  isbn,String  category); // 도서 검색
    
    ApiResponse<?> addBook(BookRequestDTO.addBookDTO addBookDTO); // 도서 등록
}
