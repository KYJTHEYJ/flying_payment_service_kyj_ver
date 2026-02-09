package com.bootcamp.paymentdemo.domain.member.dto;

public record SearchMemberResponse(
        String customerUid
        , String email
        , String name
        , String phone
        , Long pointBalance
) {
}
