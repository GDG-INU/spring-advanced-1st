package com.example.demo.exception;

// 중복된 저자를 저장했을 때 발생되는 사용자 정의 예외 클래스
public class DuplicateAuthorException extends RuntimeException {
    public DuplicateAuthorException(String message) {
        super(message);
    }
}
