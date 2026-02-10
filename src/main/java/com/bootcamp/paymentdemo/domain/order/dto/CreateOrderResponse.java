package com.bootcamp.paymentdemo.domain.order.dto;

public record CreateOrderResponse(
        String orderId
        , Integer totalAmount
        , String orderNumber
) {
}
