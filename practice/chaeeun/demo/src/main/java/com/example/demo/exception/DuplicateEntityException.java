package com.example.demo.exception;
// 중복된 member, author, publisher 등록 시 예외 발생
public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message) {
        super(message);
    }

}
