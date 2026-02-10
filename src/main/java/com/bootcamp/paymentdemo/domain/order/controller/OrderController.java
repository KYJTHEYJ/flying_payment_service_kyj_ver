package com.bootcamp.paymentdemo.domain.order.controller;

import com.bootcamp.paymentdemo.common.dto.BaseResponse;
import com.bootcamp.paymentdemo.domain.order.dto.CreateOrderRequest;
import com.bootcamp.paymentdemo.domain.order.dto.CreateOrderResponse;
import com.bootcamp.paymentdemo.domain.order.dto.SearchOrderResponse;
import com.bootcamp.paymentdemo.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<BaseResponse<CreateOrderResponse>> createOrders(
            @RequestBody CreateOrderRequest request
            , @AuthenticationPrincipal UserDetails loginMemberInfo
    ) {
        String email = loginMemberInfo.getUsername();
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(HttpStatus.CREATED.name(), null, orderService.createOrder(request, email)));
    }

    @GetMapping("/orders")
    public ResponseEntity<BaseResponse<List<SearchOrderResponse>>> searchOrders(
            @AuthenticationPrincipal UserDetails loginMemberInfo
    ) {
        String email = loginMemberInfo.getUsername();
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success(HttpStatus.OK.name(), null, orderService.searchOrderList(email)));
    }

}
