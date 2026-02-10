package com.bootcamp.paymentdemo.domain.member.controller;

import com.bootcamp.paymentdemo.common.dto.BaseResponse;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberRequest;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberResponse;
import com.bootcamp.paymentdemo.domain.member.dto.SearchMemberResponse;
import com.bootcamp.paymentdemo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<SaveMemberResponse>> signup(@RequestBody SaveMemberRequest request) {
        SaveMemberResponse response = memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success("201", "회원가입 성공", response));
    }
}
