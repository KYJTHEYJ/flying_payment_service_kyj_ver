package com.bootcamp.paymentdemo.common.security;

import com.bootcamp.paymentdemo.common.exception.ErrorEnum;
import com.bootcamp.paymentdemo.common.exception.ServiceErrorException;
import com.bootcamp.paymentdemo.domain.member.entity.MemberRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JWT 토큰 생성 및 검증 유틸리티
 * 개선할 부분: Refresh Token, Token Expiry 관리, Claims 커스터마이징 등
 */
@Slf4j
@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;

    public JwtTokenProvider(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpireTime = tokenValidityInSeconds * 1000;
        this.refreshTokenExpireTime = (tokenValidityInSeconds * 48) * 1000 * 7;
    }

    /**
     * JWT 토큰 생성
     *
     * TODO: 개선 사항
     * - 사용자 역할(Role) 정보 추가
     * - 추가 Claims 정보 (이름, 이메일 등)
     */
    public String createAccessToken(String email, String memberUid, MemberRole role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpireTime);

        return Jwts.builder()
            .subject(email)
            .claim("memberUid", memberUid)
            .claim("memberRole", role)
            .issuedAt(now)
            .expiration(validity)
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken(String email, String memberUid, MemberRole role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenExpireTime);

        return Jwts.builder()
                .subject(email)
                .claim("memberUid", memberUid)
                .claim("memberRole", role)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey)
                .compact();
    }

    /**
     * JWT 토큰에서 이메일 추출
     */
    public String getEmail(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claims.getSubject();
    }

    /**
     * JWT 토큰에서 UID 추출
     */
    public String getUid(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("memberUid").toString();
    }

    /**
     * JWT 토큰에서 만료시간 추출
     */
    public LocalDateTime getExpireTime(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * JWT 토큰 유효성 검증
     *
     * TODO: 개선 사항
     * - 토큰 블랙리스트 체크 (로그아웃된 토큰)
     * - 토큰 갱신 로직
     * - 상세한 예외 처리
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("만료 토큰 오류 : {}", e.getMessage());
            throw new ServiceErrorException(ErrorEnum.ERR_TOKEN_INVALID);
        } catch (MalformedJwtException e) {
            log.error("토큰 형식 오류 : {}", e.getMessage());
            throw new ServiceErrorException(ErrorEnum.ERR_TOKEN_INVALID);
        } catch (JwtException e) {
            log.error("토큰 검증 오류 : {}", e.getMessage());
            throw new ServiceErrorException(ErrorEnum.ERR_TOKEN_INVALID);
        }
    }
}
