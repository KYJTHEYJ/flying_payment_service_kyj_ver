package com.bootcamp.paymentdemo.common.security;

import com.bootcamp.paymentdemo.domain.member.entity.Member;
import com.bootcamp.paymentdemo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_FOUND_MEMBER;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmailAndDeletedFalse(email).orElseThrow(() -> new UsernameNotFoundException(MSG_NOT_FOUND_MEMBER));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(member.getRole().name())
                .build();
    }
}
