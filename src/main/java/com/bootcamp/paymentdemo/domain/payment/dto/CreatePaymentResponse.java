package com.bootcamp.paymentdemo.domain.payment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePaymentResponse {
    private Boolean success;
    private String paymentId;
    private String status;

    private CreatePaymentResponse(Boolean success, String paymentId, String status) {
        this.success = success;
        this.paymentId = paymentId;
        this.status = status;
    }

    public static CreatePaymentResponse register(Boolean success, String paymentId, String status) {
        return new CreatePaymentResponse(success, paymentId, status);
    }
}
