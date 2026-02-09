package com.bootcamp.paymentdemo.domain.member.controller;

import com.bootcamp.paymentdemo.common.dto.BaseResponse;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberRequest;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberResponse;
import com.bootcamp.paymentdemo.domain.member.dto.SearchMemberResponse;
import com.bootcamp.paymentdemo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<SaveMemberResponse>> signup(@RequestBody SaveMemberRequest request) {
        SaveMemberResponse response = memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success("201", "회원가입 성공", response));
    }

    // TODO 결제 테스트용, 추후 삭제 필요
    @GetMapping("/api/users")
    public ResponseEntity<SearchMemberResponse> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(new SearchMemberResponse(
                "CUST-TESTUUID"
                , "test@test.com"
                , "테스트유저"
                , "010-0000-0000"
                ,0L
        ));
    }
}
