package com.bootcamp.paymentdemo.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {
    BRONZE(1),
    SILVER(5),
    GOLD(10),
    DIAMOND(15);

    private final int pointRate;
}
