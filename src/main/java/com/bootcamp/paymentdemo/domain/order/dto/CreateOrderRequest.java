package com.bootcamp.paymentdemo.domain.order.dto;

import java.util.List;

public record CreateOrderRequest(
       List<OrderProduct> items
) {
}
