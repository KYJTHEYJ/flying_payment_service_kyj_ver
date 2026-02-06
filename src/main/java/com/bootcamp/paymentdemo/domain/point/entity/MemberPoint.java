package com.bootcamp.paymentdemo.domain.point.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member_points")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPoint extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberPointsId;
}
