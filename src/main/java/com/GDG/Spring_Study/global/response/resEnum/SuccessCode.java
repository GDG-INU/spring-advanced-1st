package com.GDG.Spring_Study.global.response.resEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    // COMMON
    DELETE_USER(200, "회원 탈퇴가 완료되었습니다."),
    FOUND_NO_SEARCH_RESULT(200, "조회결과가 없습니다."),
    ID_AVAILABLE(200, "사용 가능한 ID입니다."),
    LOGIN_SUCCESS(200, "로그인 되었습니다."),
    LOGOUT_SUCCESS(200, "로그아웃 되었습니다."),
    PASSWORD_CORRECT(200, "비밀번호가 일치합니다."),
    FOUND_IT(201, "조회가 완료되었습니다."),
    FOUND_LIST(201, "목록 조회가 완료되었습니다."),
    CREATE_USER(201, "회원가입이 완료되었습니다."),
    ADD_BOOK(201, "도서 정보가 등록되었습니다."),
    UPDATE_USER_INFO(201, "회원정보가 수정되었습니다."),
    UPDATE_USER_IMG(201, "사용자 프로필이 변경되었습니다.")
    ;

    private final Integer code;
    private final String message;
}
