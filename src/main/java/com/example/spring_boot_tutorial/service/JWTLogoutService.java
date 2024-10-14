package com.example.spring_boot_tutorial.service;

import java.util.UUID;



public interface JWTLogoutService {
    boolean existsByTokenServ(String token);

    boolean existsByTokenAndUserIdServ(String token,UUID userId);

    String createLogoutRecordServ(String accessToken,String refreshToken);
}
