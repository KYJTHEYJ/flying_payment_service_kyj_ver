package com.bootcamp.paymentdemo.domain.order.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import com.bootcamp.paymentdemo.domain.payment.entity.Payment;
import com.bootcamp.paymentdemo.domain.member.entity.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

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

    @OneToOne(mappedBy = "order")
    private Payment payment;

    private LocalDateTime orderAt;
    private LocalDateTime deletedAt;

    public static Order register(
            Member member,
            String orderNo,
            Integer totalPrice,
            Integer price,
            Integer usePoint,
            Integer savePoint,
            OrderStatus status,
            LocalDateTime orderAt
    ) {
        Order order = new Order();
        order.member = member;
        order.orderNo = orderNo;
        order.totalPrice = totalPrice;
        order.price = price;
        order.usePoint = usePoint;
        order.savePoint = savePoint;
        order.status = status;
        order.orderAt = orderAt;
        order.deleted = false;
        order.deletedAt = null;

        return order;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
        if(status == OrderStatus.REFUNDED) {
            this.deleted = true;
            this.deletedAt = LocalDateTime.now();
        }
    }

}
