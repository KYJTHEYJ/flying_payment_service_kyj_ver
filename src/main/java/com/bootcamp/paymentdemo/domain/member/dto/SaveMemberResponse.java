package com.bootcamp.paymentdemo.domain.member.dto;

import com.bootcamp.paymentdemo.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class SaveMemberResponse {

    private Long memberId;
    private String name;
    private String email;
    private String phone;

    public static SaveMemberResponse register(Member member) {
        SaveMemberResponse response = new SaveMemberResponse();
        response.memberId = member.getMemberId();
        response.name = member.getName();
        response.email = member.getEmail();
        response.phone = member.getPhoneNo();
        return response;
    }
}
