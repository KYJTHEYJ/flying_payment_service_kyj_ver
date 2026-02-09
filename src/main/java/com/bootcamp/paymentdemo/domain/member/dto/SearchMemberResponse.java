package com.bootcamp.paymentdemo.domain.member.dto;
// TODO 결제 테스트용, 삭제 필요
public record SearchMemberResponse(
        String customerUid
        , String email
        , String name
        , String phone
        , Long pointBalance
) {
}
