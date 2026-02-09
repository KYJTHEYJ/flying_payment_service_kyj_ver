package com.bootcamp.paymentdemo.domain.product.dto;

import com.bootcamp.paymentdemo.domain.product.entity.Product;
import com.bootcamp.paymentdemo.domain.product.entity.ProductStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductGetResponse {

    private Long id;
    private String name;
    private String description;
    private String category;
    private Long price;
    private Long stock;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductGetResponse register(Product product) {
        ProductGetResponse response = new ProductGetResponse();
        response.getId();
        response.getName();
        response.getDescription();
        response.getCategory();
        response.getPrice();
        response.getStock();
        response.getStatus();
        response.getCreatedAt();
        response.getUpdatedAt();
        return response;
    }

    // 프론트앤드 JSON ID 타입 String 변환
    public String getId() {
        return (id != null) ? id.toString() : "";
    }
}
