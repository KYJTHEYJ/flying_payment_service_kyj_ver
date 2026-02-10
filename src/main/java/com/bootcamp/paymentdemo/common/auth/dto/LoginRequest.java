package com.bootcamp.paymentdemo.common.auth.dto;

public record LoginRequest(
        String email
        , String password
) {
}
