package com.bootcamp.paymentdemo.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.bootcamp.paymentdemo.common.Constants.MSG_DUPLICATE_EMAIL;
import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_FOUND_MEMBER;
import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_FOUND_ORDER;

@Getter
public enum ErrorEnum {
    ERR_NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_MEMBER),

    // member exception
    ERR_DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, MSG_DUPLICATE_EMAIL),
    ERR_NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_ORDER);

    private final HttpStatus status;
    private final String message;

    ErrorEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
