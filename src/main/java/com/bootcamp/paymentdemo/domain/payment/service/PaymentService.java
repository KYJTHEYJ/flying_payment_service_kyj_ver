package com.bootcamp.paymentdemo.domain.payment.service;

import com.bootcamp.paymentdemo.common.exception.ServiceErrorException;
import com.bootcamp.paymentdemo.domain.order.entity.Order;
import com.bootcamp.paymentdemo.domain.order.repository.OrderRepository;
import com.bootcamp.paymentdemo.domain.payment.dto.CreatePaymentRequest;
import com.bootcamp.paymentdemo.domain.payment.dto.CreatePaymentResponse;
import com.bootcamp.paymentdemo.domain.payment.entity.Payment;
import com.bootcamp.paymentdemo.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.bootcamp.paymentdemo.common.exception.ErrorEnum.ERR_NOT_FOUND_ORDER;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    // TODO 결제는 포인트 사상 제외, 왠만하면 주문에서 처리하는 것으로, 안되면 로직 수정 필요
    @Transactional
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {
        Order order = orderRepository.findById(Long.valueOf(request.getOrderId())).orElseThrow(
                () -> new ServiceErrorException(ERR_NOT_FOUND_ORDER)
        );

        Payment payment = Payment.register(
                order
                , (long) request.getTotalAmount()
        );

        // 기존 주문 건이 있을 경우 (결제 창 닫아 취소 했을 경우 해당 주문 건 재활용)
        Optional<Payment> setPayment = paymentRepository.findByOrderId(Long.valueOf(request.getOrderId()));
        if(setPayment.isPresent()) {
            Payment existingPayment = setPayment.get();
            return CreatePaymentResponse.register(
                    true
                    , existingPayment.getPortOneId()
                    , existingPayment.getStatus().name()
            );
        } else {
            Payment savedPayment = paymentRepository.save(payment);
            return CreatePaymentResponse.register(
                    true
                    , savedPayment.getPortOneId()
                    , savedPayment.getStatus().name()
            );
        }
    }
}
