package com.bootcamp.paymentdemo.domain.webhook.repository;

import com.bootcamp.paymentdemo.domain.webhook.entity.Webhook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebhookRepository extends JpaRepository<Webhook, Long> {

    // 멱등성 체크
    boolean existsByRecWebhookId(String recWebhookId);
}
