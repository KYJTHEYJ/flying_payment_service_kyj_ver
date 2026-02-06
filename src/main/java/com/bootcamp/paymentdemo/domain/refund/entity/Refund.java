package com.bootcamp.paymentdemo.domain.refund.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;
}
