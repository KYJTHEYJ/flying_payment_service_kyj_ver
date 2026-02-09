package com.bootcamp.paymentdemo.domain.webhook.service;

import com.bootcamp.paymentdemo.domain.refund.service.RefundService;
import com.bootcamp.paymentdemo.domain.webhook.dto.WebhookRequest;
import com.bootcamp.paymentdemo.domain.webhook.entity.Webhook;
import com.bootcamp.paymentdemo.domain.webhook.entity.WebhookStatus;
import com.bootcamp.paymentdemo.domain.webhook.repository.WebhookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WebhookService {

    private final WebhookRepository webhookRepository;
    private final RefundService refundService;

    @Transactional
    public void process(String recWebhookId, WebhookRequest request) {
        // 멱등성 체크
        if (webhookRepository.existsByRecWebhookId(recWebhookId)) {
            return;
        }

        Webhook webhook = Webhook.register(
                recWebhookId,
                WebhookStatus.PENDING,
                null // 임시로 null 넣음
        );

        webhook.updateEventStatus(request.getStatus());

        webhookRepository.save(webhook);

        // 임시 마커로 println 사용
        if ("CANCELLED".equals(request.getStatus())) {
            refundService.processRefund(request.getPayment_id());
        } else if ("PAID".equals(request.getStatus())) {
            System.out.println("결제 완료 웹훅 수신: " + request.getPayment_id());
        }

        webhook.complete();
    }
}
