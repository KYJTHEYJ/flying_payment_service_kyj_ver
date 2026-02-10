package com.bootcamp.paymentdemo.domain.order.repository;

import com.bootcamp.paymentdemo.domain.order.entity.OrderNoSeq;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface OrderNoSeqRepository extends JpaRepository<OrderNoSeq, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
           SELECT os
           FROM OrderNoSeq os
           WHERE os.sequenceDate = :date
           """)
    Optional<OrderNoSeq> findByOrderNoSeq(@Param("date") LocalDate date);
}
