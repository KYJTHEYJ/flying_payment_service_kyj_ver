package com.bootcamp.paymentdemo.domain.member.exception;

import com.bootcamp.paymentdemo.common.exception.ErrorEnum;
import com.bootcamp.paymentdemo.common.exception.ServiceErrorException;

public class DuplicateEmailException extends ServiceErrorException {
    public DuplicateEmailException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
