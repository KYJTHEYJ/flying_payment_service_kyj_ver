package com.bootcamp.paymentdemo.domain.product.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {
    SALES,
    SOLDOUT
}
