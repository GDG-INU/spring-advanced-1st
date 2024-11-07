package com.GDG.Spring_Study.global.response;

import com.GDG.Spring_Study.global.response.resEnum.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode code;
}
