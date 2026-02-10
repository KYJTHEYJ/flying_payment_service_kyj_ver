package com.bootcamp.paymentdemo.domain.member.entity;

import com.bootcamp.paymentdemo.common.entity.Base;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String memberUid;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String phoneNo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    private LocalDateTime gradeAt;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Integer totalPriceAmount;

    @Column(nullable = false)
    private boolean deleted;

    private LocalDateTime deletedAt;

    public static Member register(SaveMemberRequest request, String encodedPassword) {
        Member member = new Member();
        member.name = request.getName();
        member.email = request.getEmail();
        member.password = encodedPassword;
        member.phoneNo = request.getPhone();
        member.memberUid = "CUST-" + UUID.randomUUID().toString().substring(0, 12);
        member.role = MemberRole.ROLE_USER;
        member.grade = Grade.BRONZE;
        member.gradeAt = LocalDateTime.now();
        member.point = 0;
        member.totalPriceAmount = 0;
        member.deleted = false;
        return member;
    }

    public void addPoint(Integer amount) {
        this.point += amount;
    }

    public void minusPoint(Integer amount) {
        this.point -= amount;
    }

    public void addTotalPriceAmount(Integer amount) {
        if (amount == null || amount < 0) return;

        if (this.totalPriceAmount == null) {
            this.totalPriceAmount = 0;
        }

        this.totalPriceAmount += amount;

        //FIXME grade 반영 로직 추가되어야함
    }

    public void subtractTotalPriceAmount(Integer amount) {
        if (amount == null || amount < 0) return;

        if (this.totalPriceAmount == null) {
            this.totalPriceAmount = 0;
        }

        // 누적 금액이 차감액보다 적으면 0으로 초기화 (음수 방지)
        if (this.totalPriceAmount >= amount) {
            this.totalPriceAmount -= amount;
        } else {
            this.totalPriceAmount = 0;
        }
    }

    public void updateGrade(Grade grade) {
        this.grade = grade;
        this.gradeAt = LocalDateTime.now();
    }
}
