package com.bootcamp.paymentdemo.domain.payment.controller;

import com.bootcamp.paymentdemo.domain.payment.dto.CreatePaymentRequest;
import com.bootcamp.paymentdemo.domain.payment.dto.CreatePaymentResponse;
import com.bootcamp.paymentdemo.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    //FIXME 결제창이 뜨지 못할 경우 -> 스케줄러?
    //FIXME 사용자가 결제를 취소할 경우 -> 동일 건 재결제 어떻게?

    //TODO Valid 처리는 테스트 이후
    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(request));
    }
}
