package com.bootcamp.paymentdemo.common.token.repository;

import com.bootcamp.paymentdemo.common.token.entity.BlackAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackAccessTokenRepository extends JpaRepository<BlackAccessToken, Long> {

    boolean existsByAccessToken(String accessToken);
}
