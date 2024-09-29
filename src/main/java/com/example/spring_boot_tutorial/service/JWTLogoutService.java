package com.example.spring_boot_tutorial.service;

import java.util.UUID;

import com.example.spring_boot_tutorial.security.UserDetailsImpl;

public interface JWTLogoutService {
    Boolean existsByTokenServ(String token);

    Boolean existsByTokenAndUserIdServ(String token,UUID userId);

    String createLogoutRecord(UserDetailsImpl userDetailsImpl, String token);
}
