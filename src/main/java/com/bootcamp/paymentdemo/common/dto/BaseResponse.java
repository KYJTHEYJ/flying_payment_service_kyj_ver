package com.bootcamp.paymentdemo.common.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"success", "code", "message", "data"})
public class BaseResponse<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(String code, String message, T data) {
        BaseResponse<T> response = new BaseResponse<>();

        response.success = true;
        response.code = code;
        response.message = message;
        response.data = data;

        return response;
    }

    public static <T> BaseResponse<T> fail(String code, String message, T data) {
        BaseResponse<T> response = new BaseResponse<>();

        response.success = false;
        response.code = code;
        response.message = message;
        response.data = data;

        return response;
    }
}
