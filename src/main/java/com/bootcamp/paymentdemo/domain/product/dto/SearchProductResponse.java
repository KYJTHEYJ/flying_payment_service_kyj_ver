package com.bootcamp.paymentdemo.domain.product.dto;

public record SearchProductResponse(
        String id
        , String name
        , Long price
        , Long stock
) {
}
