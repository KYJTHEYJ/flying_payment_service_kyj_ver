package com.bootcamp.paymentdemo.domain.member.controller;

import com.bootcamp.paymentdemo.domain.member.dto.SearchMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
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
