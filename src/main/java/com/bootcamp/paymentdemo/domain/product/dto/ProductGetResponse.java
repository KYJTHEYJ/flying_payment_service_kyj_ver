package com.bootcamp.paymentdemo.domain.product.dto;

import com.bootcamp.paymentdemo.domain.product.entity.ProductStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductGetResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final String category;
    private final Long price;
    private final Long stock;
    private final ProductStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProductGetResponse(
            Long id,
            String name,
            String description,
            String category,
            Long price,
            Long stock,
            ProductStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 프론트앤드 JSON ID 타입 String 변환
    public String getId() {
        return (id != null) ? id.toString() : "";
    }
}
