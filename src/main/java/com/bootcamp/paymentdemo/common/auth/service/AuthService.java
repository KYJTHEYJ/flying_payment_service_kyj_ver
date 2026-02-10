package com.bootcamp.paymentdemo.common.auth.service;

import com.bootcamp.paymentdemo.common.auth.dto.LoginInfoResponse;
import com.bootcamp.paymentdemo.common.auth.dto.LoginRequest;
import com.bootcamp.paymentdemo.common.auth.dto.RefreshResponse;
import com.bootcamp.paymentdemo.common.exception.ErrorEnum;
import com.bootcamp.paymentdemo.common.exception.ServiceErrorException;
import com.bootcamp.paymentdemo.common.security.JwtTokenProvider;
import com.bootcamp.paymentdemo.common.token.dto.TokenInfo;
import com.bootcamp.paymentdemo.common.token.entity.BlackAccessToken;
import com.bootcamp.paymentdemo.common.token.entity.RefreshToken;
import com.bootcamp.paymentdemo.common.token.repository.BlackAccessTokenRepository;
import com.bootcamp.paymentdemo.common.token.repository.RefreshTokenRepository;
import com.bootcamp.paymentdemo.domain.member.entity.Member;
import com.bootcamp.paymentdemo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.bootcamp.paymentdemo.common.exception.ErrorEnum.ERR_NOT_FOUND_MEMBER;
import static com.bootcamp.paymentdemo.common.exception.ErrorEnum.ERR_TOKEN_EXPIRE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlackAccessTokenRepository blackAccessTokenRepository;

    @Transactional
    public TokenInfo login(LoginRequest request) {
        Member member = memberRepository.findByEmailAndDeletedFalse(request.email()).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_NOT_MATCH_LOGIN));

        // JWT 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(request.email(), member.getMemberUid(), member.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(request.email(), member.getMemberUid(), member.getRole());

        refreshTokenRepository.save(RefreshToken.register(member, refreshToken, jwtTokenProvider.getExpireTime(refreshToken)));

        return new TokenInfo(accessToken, refreshToken);
    }

    @Transactional
    public void logout(String accessToken, String email) {
        RefreshToken refreshToken = refreshTokenRepository.findByEmail(email).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_TOKEN_EMPTY));
        refreshTokenRepository.delete(refreshToken);

        blackAccessTokenRepository.save(BlackAccessToken.register(accessToken, jwtTokenProvider.getExpireTime(accessToken)));
    }

    @Transactional(readOnly = true)
    public LoginInfoResponse getLoginMemberInfo(String email) {
        Member member = memberRepository.findByEmailAndDeletedFalse(email).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_NOT_FOUND_MEMBER));

        return new LoginInfoResponse(
                member.getMemberUid()
                , member.getEmail()
                , member.getName()
                , member.getPhoneNo()
                , member.getPoint()
        );
    }

    @Transactional
    public RefreshResponse refreshToken(String refreshToken) {
        if(jwtTokenProvider.validateToken(refreshToken)) {
            RefreshToken existRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new ServiceErrorException(ErrorEnum.ERR_TOKEN_EMPTY));

            // 리프레쉬 토큰이 만료 되었는지 확인
            if(existRefreshToken.getExpirationAt().isBefore(LocalDateTime.now())) {
                refreshTokenRepository.delete(existRefreshToken);
                throw new ServiceErrorException(ERR_TOKEN_EXPIRE);
            }

            // 유저 정보 가져오기
            Member member = existRefreshToken.getMember();
            if(!memberRepository.existsByEmailAndDeletedFalse(member.getEmail())) {
                refreshTokenRepository.delete(existRefreshToken);
                throw new ServiceErrorException(ERR_NOT_FOUND_MEMBER);
            }

            // 기존 토큰 삭제
            refreshTokenRepository.delete(existRefreshToken);

            // 새 토큰 발급
            String newAccessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getMemberUid(), member.getRole());
            String newRefreshToken = jwtTokenProvider.createRefreshToken(member.getEmail(), member.getMemberUid(), member.getRole());

            // 새 리프레쉬 토큰 저장
            refreshTokenRepository.save(RefreshToken.register(member, newRefreshToken, jwtTokenProvider.getExpireTime(newRefreshToken)));

            return new RefreshResponse(newAccessToken, newRefreshToken);
        } else {
            log.error("Refresh Tokens Error : {}", "리프레쉬 토큰 올바르지 않음");
            throw new ServiceErrorException(ErrorEnum.ERR_TOKEN_INVALID);
        }
    }
}
