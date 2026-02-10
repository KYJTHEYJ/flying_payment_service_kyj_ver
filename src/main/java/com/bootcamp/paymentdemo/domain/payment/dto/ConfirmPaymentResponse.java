package com.bootcamp.paymentdemo.domain.payment.dto;

public record ConfirmPaymentResponse(
        Boolean success
        , String orderId
        , String status
) {

}
