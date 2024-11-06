package com.GDG.Spring_Study.global.response.resEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 4xx
    ALREADY_EXISTING(400, HttpStatus.BAD_REQUEST, "이미 존재하는 값입니다."),
    ALREADY_EXISTING_BOOK(400, HttpStatus.BAD_REQUEST, "이미 존재하는 도서 정보입니다."),
    INVALID_PARAMETER(400, HttpStatus.BAD_REQUEST, "파라미터 값을 다시 확인해주세요."),
    LOGIN_FAIL(400, HttpStatus.BAD_REQUEST, "로그인에 실패했습니다."),
    UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED, "액세스 권한이 없습니다."),
    PASSWORD_INCORRECT(401, HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    SESSION_EXPIRED(403, HttpStatus.FORBIDDEN, "세션이 만료되었습니다."),
    RESOURCE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Resource not found"),
    BOOK_NOT_FOUND(404, HttpStatus.NOT_FOUND, "도서 정보를 찾을 수 없습니다."),
    SAVE_SOME_FAIL(404, HttpStatus.NOT_FOUND, "일부 값을 저장하지 못했습니다."),
    // 5xx
    CREATE_FAIL(500, HttpStatus.INTERNAL_SERVER_ERROR, "객체 생성에 실패했습니다.")
    ;

    private final Integer code;
    private final HttpStatus status;
    private final String message;
}
