package com.bootcamp.paymentdemo.domain.member.service;

import com.bootcamp.paymentdemo.common.exception.ErrorEnum;
import com.bootcamp.paymentdemo.common.exception.ServiceErrorException;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberRequest;
import com.bootcamp.paymentdemo.domain.member.dto.SaveMemberResponse;
import com.bootcamp.paymentdemo.domain.member.entity.Member;
import com.bootcamp.paymentdemo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    @Transactional
    public SaveMemberResponse signup(SaveMemberRequest request) {
        if (memberRepository.existsByEmailAndDeletedFalse(request.getEmail())) {
            throw new ServiceErrorException(ErrorEnum.ERR_DUPLICATE_EMAIL);
        }

        Member member = Member.register(request, passwordEncoder.encode(request.getPassword()));
        memberRepository.save(member);
        return SaveMemberResponse.register(member);
    }
}
