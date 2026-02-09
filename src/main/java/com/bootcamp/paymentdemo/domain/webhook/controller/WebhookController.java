package com.bootcamp.paymentdemo.domain.webhook.controller;

import com.bootcamp.paymentdemo.domain.webhook.dto.WebhookRequest;
import com.bootcamp.paymentdemo.domain.webhook.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping("/portone")
    public ResponseEntity<Void> handlePortOneWebhook(
            @RequestHeader("webhook-id") String webhookId,
            @RequestBody WebhookRequest request
    ) {
        webhookService.process(webhookId, request);

        return ResponseEntity.ok().build();
    }
}
