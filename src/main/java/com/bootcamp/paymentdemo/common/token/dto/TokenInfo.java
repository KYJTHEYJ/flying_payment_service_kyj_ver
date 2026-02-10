package com.bootcamp.paymentdemo.common.token.dto;

public record TokenInfo (
        String accessToken
        , String refreshToken
) {

}
