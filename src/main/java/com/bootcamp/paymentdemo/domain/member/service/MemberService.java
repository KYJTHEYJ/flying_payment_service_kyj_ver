package com.bootcamp.paymentdemo.domain.member.service;

import com.bootcamp.paymentdemo.common.exception.ErrorEnum;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberRequest;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberResponse;
import com.bootcamp.paymentdemo.domain.member.entity.Member;
import com.bootcamp.paymentdemo.domain.member.exception.DuplicateEmailException;
import com.bootcamp.paymentdemo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public SaveMemberResponse signup(SaveMemberRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(ErrorEnum.DUPLICATE_EMAIL);
        }

        Member member = Member.register(request, passwordEncoder.encode(request.getPassword()));
        memberRepository.save(member);
        return SaveMemberResponse.register(member);
    }
}
