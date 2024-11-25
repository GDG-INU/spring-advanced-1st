package com.example.demo.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// 글로벌 예외 처리기
// -> 유효성 검사, 사용자 정의 예외, 일반적인 서버 오류(NullPointerException)
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 유효성 검사 실패 -> MethodArgumentNotValidException는 Spring에서 제공하는 예외
    // 반환값을 유효하지 않은 필드 이름, 해당 에러 메시지로 구성된 Map<String, String> 형식
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors); // 400 Bad Request
    }

    // 중복된 member, author, publisher 등록 시 예외 처리
    @ExceptionHandler(DuplicateEntityException.class) // 예외 객체를 매개변수 ex로 받는다.
    public ResponseEntity<String> handleDuplicateEntityException(DuplicateEntityException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409 Conflict
    }

    // Rental 할 때 존재하지 않은 memberId나 bookId 입력 시 예외 처리 + 반납할 때 대여 기록이 존재하지 않을 때
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // 이미 대여된 책을 대여하려고 할 때 예외 처리(대여 불가능 책)
    @ExceptionHandler(AlreadyRentedException.class)
    public ResponseEntity<String> handleAlreadyRentedException(AlreadyRentedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}