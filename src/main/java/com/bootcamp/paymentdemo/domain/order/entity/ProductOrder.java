package com.bootcamp.paymentdemo.domain.order.entity;


import com.bootcamp.paymentdemo.common.entity.Base;
import com.bootcamp.paymentdemo.domain.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "products_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOrder extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOrderId;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private Long quantity;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Order order;

    public ProductOrder(
            String name,
            Long price,
            Long quantity,
            Boolean deleted,
            LocalDateTime deletedAt
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }
}
