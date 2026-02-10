package com.bootcamp.paymentdemo.common.token.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "black_access_tokens")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlackAccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String accessToken;

    private LocalDateTime expirationAt;

    private LocalDateTime listedAt;

    private BlackAccessToken(String accessToken, LocalDateTime expirationAt) {
        this.accessToken = accessToken;
        this.expirationAt = expirationAt;
        this.listedAt = LocalDateTime.now();
    }

    public static BlackAccessToken register(String accessToken, LocalDateTime expirationAt) {
        return new BlackAccessToken(accessToken, expirationAt);
    }
}
