package com.example.spring_boot_tutorial.service;

import java.util.UUID;

import org.springframework.security.core.Authentication;


public interface JWTLogoutService {
    boolean existsByTokenServ(String token);

    boolean existsByTokenAndUserIdServ(String token,UUID userId);

    String createLogoutRecordServ(String accessToken,String refreshToken);
}
