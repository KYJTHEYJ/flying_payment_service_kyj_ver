package com.bootcamp.paymentdemo.domain.point.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import com.bootcamp.paymentdemo.domain.member.entity.Member;
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

    @Column(nullable = false)
    private Integer currentPoint;

    @Column(nullable = false)
    private Integer usingPoint;

    @Column(nullable = false)
    private Integer savingPoint;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
