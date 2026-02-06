package com.bootcamp.paymentdemo.domain.order.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String orderNo;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer usePoint;

    @Column(nullable = false)
    private Integer savePoint;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private LocalDateTime cancelAt;

    public Order(
            String orderNo,
            Integer totalPrice,
            Integer price,
            Integer usePoint,
            Integer savePoint,
            OrderStatus status,
            Boolean deleted,
            LocalDateTime orderDate,
            LocalDateTime cancelAt
    ) {
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.price = price;
        this.usePoint = usePoint;
        this.savePoint = savePoint;
        this.status = status;
        this.deleted = deleted;
        this.orderDate = orderDate;
        this.cancelAt = cancelAt;
    }

}
