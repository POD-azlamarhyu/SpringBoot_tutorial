package com.example.spring_boot_tutorial.service;

import java.util.UUID;

import com.example.spring_boot_tutorial.security.UserDetailsImpl;

public interface JWTLogoutService {
    boolean existsByTokenServ(String token);

    boolean existsByTokenAndUserIdServ(String token,UUID userId);

    String createLogoutRecordServ(UserDetailsImpl userDetailsImpl, String authToken);
}
