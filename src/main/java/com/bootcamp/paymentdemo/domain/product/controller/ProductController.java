package com.bootcamp.paymentdemo.domain.product.controller;

import com.bootcamp.paymentdemo.domain.product.dto.SearchProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    @GetMapping("/api/products")
    public ResponseEntity<List<SearchProductResponse>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of(
                new SearchProductResponse(
                        "PROD-TESTUUID"
                        , "테스트 상품"
                        , 1000L
                        , 100L
                )
        ));
    }
}
