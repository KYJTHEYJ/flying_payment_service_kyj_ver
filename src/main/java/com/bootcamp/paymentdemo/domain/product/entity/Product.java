package com.bootcamp.paymentdemo.domain.product.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import jakarta.persistence.*;
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

    //private String productUid;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, length = 225)
    private String description;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private LocalDateTime deletedAt;

    private Product(String name, String category, String description, Long price, Long stock) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = ProductStatus.SALES;
        this.deleted = false;
        this.deletedAt = null;
    }

    public static Product register(
            String name,
            String category,
            String description,
            Long price,
            Long stock
    ) {
        return new Product(name, category, description, price, stock);
    }

    public void updateStatus(ProductStatus status) {
        this.status = status;
    }

    public void updateStock(Long stock) {
        this.stock -= stock;

        if(this.stock > 0) {
            this.status = ProductStatus.SALES;
        } else {
            this.status = ProductStatus.SOLDOUT;
        }
    }
}
