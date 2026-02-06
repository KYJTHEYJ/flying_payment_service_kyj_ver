package com.bootcamp.paymentdemo.domain.product.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String category;

    @NotBlank
    @Column(nullable = false, length = 225)
    private String description;

    @Column(nullable = false)
    private boolean deleted;

    @NotNull
    private Long price;
    private Long stock;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private LocalDateTime deletedAt;

    public Product(
            String name,
            String category,
            String description,
            Boolean deleted,
            Long price,
            Long stock,
            ProductStatus status
    ) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.deleted = deleted;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

}
