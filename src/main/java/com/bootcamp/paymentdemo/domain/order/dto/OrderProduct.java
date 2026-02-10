package com.bootcamp.paymentdemo.domain.order.dto;

public record OrderProduct(
        Long productId
        , Long quantity
) {
}
