package com.bootcamp.paymentdemo.domain.member.dto;

import lombok.Getter;

@Getter
public class SaveMemberRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
}
