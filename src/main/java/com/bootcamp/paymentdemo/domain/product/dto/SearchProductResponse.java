package com.bootcamp.paymentdemo.domain.product.dto;
// TODO 결제 테스트용, 삭제 필요
public record SearchProductResponse(
        String id
        , String name
        , Long price
        , Long stock
) {
}
