package com.example.spring_boot_tutorial.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.payload.LoginDTO;
import com.example.spring_boot_tutorial.payload.RegisterDTO;

public interface AuthService {

    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);

    ResponseEntity<?> refreshToken(Optional<RefreshToken> refreshToken);
}
