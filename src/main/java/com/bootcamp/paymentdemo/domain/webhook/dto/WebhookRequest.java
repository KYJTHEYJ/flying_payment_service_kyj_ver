package com.bootcamp.paymentdemo.domain.webhook.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebhookRequest {

    private String payment_id;
    private String status;
    private String event_type;
}
