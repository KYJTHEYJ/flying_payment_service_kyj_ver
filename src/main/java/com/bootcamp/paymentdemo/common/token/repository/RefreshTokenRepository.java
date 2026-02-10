package com.bootcamp.paymentdemo.common.token.repository;

import com.bootcamp.paymentdemo.common.token.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query("""
            SELECT r
            FROM RefreshToken r
            JOIN FETCH r.member m
            WHERE m.email = :email
            and m.deleted = false
           """)
    Optional<RefreshToken> findByEmail(String email);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
