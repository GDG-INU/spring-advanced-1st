package com.GDG.Spring_Study.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.GDG.Spring_Study.global.response.resEnum.ErrorCode;
import com.GDG.Spring_Study.global.response.resEnum.ResponseStatus;
import com.GDG.Spring_Study.global.response.resEnum.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.GDG.Spring_Study.global.response.resEnum.ResponseStatus.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private ResponseStatus status;
    private T data;

    public ApiResponse(Integer code, ResponseStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ApiResponse(Integer code, ResponseStatus status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> SUCCESS (SuccessCode code) {
        return new ApiResponse<>(code.getCode(), SUCCESS, code.getMessage());
    }
    public static <T> ApiResponse<T> SUCCESS (SuccessCode code, T data) {
        return new ApiResponse<>(code.getCode(), SUCCESS, code.getMessage(), data);
    }
    public static <T> ApiResponse<T> ERROR (ErrorCode code) {
        return new ApiResponse<>(code.getCode(), ERROR, code.getMessage());
    }
}