package com.bootcamp.paymentdemo.common.auth.controller;

import com.bootcamp.paymentdemo.common.auth.dto.*;
import com.bootcamp.paymentdemo.common.auth.service.AuthService;
import com.bootcamp.paymentdemo.common.dto.BaseResponse;
import com.bootcamp.paymentdemo.common.security.JwtTokenProvider;
import com.bootcamp.paymentdemo.common.token.dto.TokenInfo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.bootcamp.paymentdemo.common.Constants.MSG_LOGOUT;
import static com.bootcamp.paymentdemo.common.Constants.MSG_NOT_MATCH_LOGIN;

/**
 * 인증 관련 API 컨트롤러
 * 구현할 API 엔드포인트 템플릿
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    /**
     * 로그인 API
     * POST /api/auth/login
     *
     * 요청 본문:
     * {
     *   "email": "user@example.com",
     *   "password": "password123"
     * }
     *
     * 응답 헤더:
     * Authorization: Bearer eyJhbGc...
     *
     * 응답 본문:
     * {
     *   "success": true,
     *   "email": "user@example.com"
     * }
     */
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(
            @RequestBody LoginRequest request
            , HttpServletResponse response
    ) {
        String email = request.email();

        try {
            // AuthenticationManager를 통한 인증
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );

            // JWT 토큰 생성
            TokenInfo tokens = authService.login(request);

            // 리프레쉬 토큰은 쿠키로 반환
            Cookie refreshTokenCookie = new Cookie("refreshToken", tokens.refreshToken());
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setMaxAge(3600 * 24 * 7);
            //refreshTokenCookie.setHttpOnly(true); // 현재는 환경상 주석처리
            response.addCookie(refreshTokenCookie);

            // 액세스 토큰은 헤더로 반환
            return ResponseEntity.ok()
                .header("Authorization", "Bearer " + tokens.accessToken())
                .body(BaseResponse.success(HttpStatus.OK.name(), null, new LoginResponse(true, email)));

        } catch (AuthenticationException e) {
            log.error("Login Error : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.fail(HttpStatus.UNAUTHORIZED.name(), MSG_NOT_MATCH_LOGIN, new LoginResponse(false, MSG_NOT_MATCH_LOGIN)));
        }
    }

    // 로그아웃
    // TODO 로그아웃 버튼에 붙여야함
    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<Void>> logout(
            @AuthenticationPrincipal UserDetails loginMemberInfo
            , @RequestHeader(HttpHeaders.AUTHORIZATION) String accessTokenWithBearer
    ) {
        String email = loginMemberInfo.getUsername();
        String accessToken = accessTokenWithBearer.substring("Bearer ".length());

        authService.logout(accessToken, email);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success(HttpStatus.OK.name(), MSG_LOGOUT, null));

    }

    // Refresh Token 을 통한 재발급
    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<RefreshResponse>> refresh(@RequestBody RefreshRequest request) {
        RefreshResponse response = authService.refreshToken(request.refreshToken());
        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", "Bearer " + response.accessToken())
                .body(BaseResponse.success(HttpStatus.OK.name(), null, response));
    }

    /**
     * 현재 로그인한 사용자 정보 조회 API
     * GET /api/auth/me
     *
     * 응답:
     * {
     *   "success": true,
     *   "email": "user@example.com",
     *   "customerUid": "CUST_xxxxx",
     *   "name": "홍길동"
     * }
     *
     * 중요: customerUid는 PortOne 빌링키 발급 시 활용!
     */
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<LoginInfoResponse>> getCurrentUser(@AuthenticationPrincipal UserDetails loginMemberInfo) {
        String email = loginMemberInfo.getUsername();
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success(HttpStatus.OK.name(), null, authService.getLoginMemberInfo(email)));
    }
}
