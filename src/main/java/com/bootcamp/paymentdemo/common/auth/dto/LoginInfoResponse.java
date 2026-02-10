package com.bootcamp.paymentdemo.common.auth.dto;

import java.math.BigDecimal;

public record LoginInfoResponse(
        String customerUid
        , String email
        , String name
        , String phone
        , Integer pointBalance
) {
}
