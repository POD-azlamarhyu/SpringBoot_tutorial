package com.example.spring_boot_tutorial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.payload.LoginDTO;
import com.example.spring_boot_tutorial.payload.RegisterDTO;
import com.example.spring_boot_tutorial.security.JWTTokenProvider;
import com.example.spring_boot_tutorial.service.AuthService;
import com.example.spring_boot_tutorial.service.JWTLogoutService;
import com.example.spring_boot_tutorial.service.RefreshTokenService;
import com.example.spring_boot_tutorial.utils.JWTAuthResponse;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTLogoutService jwtLogoutService;

    @Autowired
    RefreshTokenService refreshTokenService;

    
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
        Authentication authentication,
        @RequestHeader("Authorization") String token
    ){
        String response = jwtLogoutService.createLogoutRecordServ(authentication, token);

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> refreshToken(
        @RequestBody String refreshToken
    ){
        Optional<RefreshToken> refreshTokenRercord = refreshTokenService.getByTokenAndUser(refreshToken);
        
        return authService.refreshToken(refreshTokenRercord);
    }
}
