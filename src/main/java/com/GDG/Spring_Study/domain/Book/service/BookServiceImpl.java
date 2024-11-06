package com.GDG.Spring_Study.domain.Book.service;

import com.GDG.Spring_Study.domain.Book.BookRepository;
import com.GDG.Spring_Study.domain.Book.dto.BookRequestDTO;
import com.GDG.Spring_Study.domain.Book.dto.BookResponseDTO;
import com.GDG.Spring_Study.entitiy.Book;
import com.GDG.Spring_Study.global.response.ApiResponse;
import com.GDG.Spring_Study.global.response.CustomException;
import com.GDG.Spring_Study.global.response.resEnum.ErrorCode;
import com.GDG.Spring_Study.global.response.resEnum.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if(bookInfo == null) return ApiResponse.ERROR(ErrorCode.BOOK_NOT_FOUND);

        // 조회된 데이터 dto로 변환
        List<BookResponseDTO.searchBookDTO> bookDTOList = new ArrayList<>();
        for (Book entity : bookInfo)
            bookDTOList.add(BookResponseDTO.searchBookDTO.builder()
                            .title(entity.getTitle())
                            .author(entity.getAuthor())
                            .isbn(entity.getIsbn())
                            .publisher(entity.getPublisher())
                            .category(entity.getCategory())
                            .coverImg(entity.getCoverImg())
                            .build());

        return ApiResponse.SUCCESS(SuccessCode.FOUND_LIST, bookDTOList);
    }

    /**
     * 도서 등록
     * @param addBookDTO
     * @return SuccessCode or ErrorCode
     */
    @Override
    public ApiResponse<?> addBook(BookRequestDTO.addBookDTO addBookDTO) {
        // 1. isbn 기준으로 이미 등록된 도서가 있는지 확인
        Optional<Book> bookInfo = bookRepository.findByIsbn(addBookDTO.getIsbn());

        // 2. 이미 등록된 도서 정보가 없는 경우에만 등록
        if (bookInfo.isEmpty()) {
            Book book = Book.builder()
                    .title(addBookDTO.getTitle())
                    .author(addBookDTO.getAuthor())
                    .publisher(addBookDTO.getPublisher())
                    .isbn(addBookDTO.getIsbn())
                    .category(addBookDTO.getCategory())
                    .coverImg(addBookDTO.getCoverImg())
                    .build();
            bookRepository.save(book);
            return ApiResponse.SUCCESS(SuccessCode.ADD_BOOK);
        } else return ApiResponse.ERROR(ErrorCode.ALREADY_EXISTING_BOOK);
    }

    /**
     * 도서 삭제
     * @param id
     * @return ApiResponse<?>
     */
    @Override
    @Transactional
    public ApiResponse<?> deleteBook(Long id) {
        bookRepository.deleteById(id);
        return ApiResponse.SUCCESS(SuccessCode.DELETE_BOOK);
    }
}
