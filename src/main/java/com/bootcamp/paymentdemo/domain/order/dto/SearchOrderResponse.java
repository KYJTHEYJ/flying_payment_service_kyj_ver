package com.bootcamp.paymentdemo.domain.order.dto;

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
