package com.bootcamp.paymentdemo.domain.order.dto;

public record CreateOrderResponse(
        String orderId
        , Long totalAmount
        , String orderNumber
) {
}
