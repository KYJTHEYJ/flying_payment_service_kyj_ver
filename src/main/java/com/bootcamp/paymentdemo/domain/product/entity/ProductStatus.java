package com.bootcamp.paymentdemo.domain.product.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {
    SALES("판매중"),
    SOLDOUT("품절");

    private final String value;
}
