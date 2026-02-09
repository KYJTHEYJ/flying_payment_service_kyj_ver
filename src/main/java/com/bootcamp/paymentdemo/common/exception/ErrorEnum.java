package com.bootcamp.paymentdemo.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_FOUND_MEMBER;
import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_FOUND_PRODUCT;

@Getter
public enum ErrorEnum {
    ERR_NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_MEMBER)
    , ERR_NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_PRODUCT)
      
    // 환불 관련
    ERR_NOT_FOUND_PAYMENT(HttpStatus.NOT_FOUND, MSG_NOT_FOUND_PAYMENT),
    ERR_ALREADY_REFUNDED(HttpStatus.BAD_REQUEST, MSG_ALREADY_REFUNDED),
    ERR_INVALID_REFUND_STATUS(HttpStatus.BAD_REQUEST, MSG_INVALID_REFUND_STATUS),

    // member exception
    ERR_DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, MSG_DUPLICATE_EMAIL);

    private final HttpStatus status;
    private final String message;

    ErrorEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
