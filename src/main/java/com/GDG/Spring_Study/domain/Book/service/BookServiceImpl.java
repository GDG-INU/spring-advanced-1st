package com.GDG.Spring_Study.domain.Book.service;

import com.GDG.Spring_Study.domain.Book.BookRepository;
import com.GDG.Spring_Study.entitiy.Book;
import com.GDG.Spring_Study.global.response.ApiResponse;
import com.GDG.Spring_Study.global.response.resEnum.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    /**
     * 도서 검색
     * @param author
     * @param publisher
     * @param title
     * @param isbn
     * @param category
     * @return ApiResponse<?>, List<?> 검색한 파일 리스트
     */
    @Override
    public ApiResponse<?> searchBook(String author, String publisher, String title, String isbn, String category) {
        List<Book> bookInfo = bookRepository.findByFilters(author, publisher, title, isbn, category);
        return ApiResponse.SUCCESS(SuccessCode.FOUND_LIST, bookInfo);
    }
}
