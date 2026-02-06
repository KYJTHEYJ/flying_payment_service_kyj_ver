package com.bootcamp.paymentdemo.domain.order.entity;


import com.bootcamp.paymentdemo.common.entity.Base;
import com.bootcamp.paymentdemo.domain.product.entity.Product;
import jakarta.persistence.*;
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

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
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

    public static ProductOrder register(
            Product product,
            Order order,
            String name,
            Long price,
            Long quantity
    ) {
        ProductOrder productOrder = new ProductOrder();

        productOrder.product = product;
        productOrder.order = order;
        productOrder.name = name;
        productOrder.price = price;
        productOrder.quantity = quantity;
        productOrder.deleted = false;
        productOrder.deletedAt = null;

        return productOrder;
    }
}
