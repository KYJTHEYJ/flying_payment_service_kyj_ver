package com.bootcamp.paymentdemo.common.auth.dto;

public record LoginResponse(
        Boolean success,
        String email
) {
}
