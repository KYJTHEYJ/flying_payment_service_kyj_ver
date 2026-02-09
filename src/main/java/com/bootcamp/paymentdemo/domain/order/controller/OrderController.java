package com.bootcamp.paymentdemo.domain.order.controller;

import com.bootcamp.paymentdemo.domain.order.dto.CreateOrderResponse;
import com.bootcamp.paymentdemo.domain.order.dto.SearchOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    // TODO 결제 테스트용, 삭제 필요
    @PostMapping("/api/orders")
    public ResponseEntity<CreateOrderResponse> createOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new CreateOrderResponse(
                        "1"
                        , 1000L
                        , "ORDER-20260209-0001"
                )
        );
    }

    // TODO 결제 테스트용, 삭제 필요
    @GetMapping("/api/orders")
    public ResponseEntity<List<SearchOrderResponse>> searchOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(
                List.of(
                        new SearchOrderResponse(
                                "ORDER-20260209-0001"
                                , "1"
                                , 1000
                                , 0
                                , 1000
                                , 10
                                , "KRW"
                                , "PENDING"
                                , LocalDateTime.now().toString()
                        )
                )
        );
    }
}
