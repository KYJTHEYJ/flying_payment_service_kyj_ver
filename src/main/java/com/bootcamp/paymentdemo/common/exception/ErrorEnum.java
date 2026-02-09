package com.bootcamp.paymentdemo.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_FOUND_MEMBER;
import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_FOUND_PRODUCT;

@Getter
public enum ErrorEnum {
    ERR_NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_MEMBER)
    , ERR_NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_PRODUCT);

    private final HttpStatus status;
    private final String message;

    ErrorEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
