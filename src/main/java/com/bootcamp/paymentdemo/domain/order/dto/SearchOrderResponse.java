package com.bootcamp.paymentdemo.domain.order.dto;
// TODO 결제 테스트용, 삭제 필요
public record SearchOrderResponse(
       String orderNumber
       , String orderId
       , Integer totalAmount
       , Integer usedPoints
       , Integer finalAmount
       , Integer earnedPoints
       , String currency
       , String status
       , String createdAt
) {
}
