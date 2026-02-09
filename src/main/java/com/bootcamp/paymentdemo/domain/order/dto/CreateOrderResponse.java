package com.bootcamp.paymentdemo.domain.order.dto;
// TODO 결제 테스트용, 삭제 필요
public record CreateOrderResponse(
        String orderId
        , Long totalAmount
        , String orderNumber
) {
}
