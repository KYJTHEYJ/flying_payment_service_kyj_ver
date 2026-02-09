package com.bootcamp.paymentdemo.domain.product.controller;

import com.bootcamp.paymentdemo.domain.product.dto.ProductGetResponse;
import com.bootcamp.paymentdemo.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductGetResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetResponse> getOne(
            @PathVariable ("id") Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findOne(id));
    }
}
