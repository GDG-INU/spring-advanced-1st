package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

// 글로벌 예외 처리기
// -> 유효성 검사, 사용자 정의 예외, 일반적인 서버 오류(NullPointerException)
@ControllerAdvice
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

    // 중복된 저자 이름
    @ExceptionHandler(DuplicateAuthorException.class) // 예외 객체를 매개변수 ex로 받는다.
    public ResponseEntity<String> handleDuplicateAuthorException(DuplicateAuthorException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409 Conflict
    }
    
    // 중복된 출판사 이름
    @ExceptionHandler(DuplicatePublisherException.class)
    public ResponseEntity<String> handleDuplicatePublisherException(DuplicatePublisherException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409 Conflict
    }

    // 중복된 회원(이메일로 구별)
    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<String> handleDuplicateMemberException(DuplicateMemberException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}


