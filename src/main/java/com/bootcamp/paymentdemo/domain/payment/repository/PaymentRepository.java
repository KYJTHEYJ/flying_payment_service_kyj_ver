package com.bootcamp.paymentdemo.domain.payment.repository;

import com.bootcamp.paymentdemo.domain.order.entity.Order;
import com.bootcamp.paymentdemo.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("""
            SELECT p
            FROM Payment p
            JOIN FETCH p.order o
            WHERE p.deleted = false
            AND p.status = 'PENDING'
            AND o.orderId = :order_id
          """)
    Optional<Payment> findByOrderId(@Param("order_id") Long orderId);

    Optional<Payment> findByPortOneId(String portOneId);
}
