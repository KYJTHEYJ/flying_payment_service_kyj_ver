package com.bootcamp.paymentdemo.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    PENDING("준비중"),
    COMPLETE("완료"),
    REFUNDED("환불처리");

    private final String value;
}
