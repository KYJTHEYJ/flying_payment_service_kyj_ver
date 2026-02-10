package com.bootcamp.paymentdemo.domain.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "order_no_sequence")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderNoSeq {
    @Id
    LocalDate sequenceDate;

    private Long orderNoSeq;

    private OrderNoSeq(LocalDate sequenceDate) {
        this.sequenceDate = sequenceDate;
        this.orderNoSeq = 1L;
    }

    public static OrderNoSeq register(LocalDate sequenceDate) {
        return new OrderNoSeq(sequenceDate);
    }

    public void nextSequence() {
       orderNoSeq++;
    }
}
