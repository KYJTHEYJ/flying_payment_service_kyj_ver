package com.bootcamp.paymentdemo.domain.point.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member_point_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPointLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long point_log_id;
}
