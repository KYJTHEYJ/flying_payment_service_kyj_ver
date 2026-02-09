package com.bootcamp.paymentdemo.domain.point.repository;

import com.bootcamp.paymentdemo.domain.point.entity.MemberPointLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPointLogRepository extends JpaRepository<MemberPointLog, Long> {


}
