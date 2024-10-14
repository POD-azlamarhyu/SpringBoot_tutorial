package com.example.spring_boot_tutorial.controller;

import java.util.Map;
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
import com.example.spring_boot_tutorial.utils.JWTLoginResponse;


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
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        Map<String, String> tokens = authService.login(loginDTO);
        JWTLoginResponse jwtLoginResponse = new JWTLoginResponse();
        jwtLoginResponse.setAccessToken(tokens.get("access_token"));
        jwtLoginResponse.setRefreshToken(tokens.get("refresh_token"));
        return ResponseEntity.ok(jwtLoginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
        @RequestHeader("Authorization") String accessToken,
        @RequestBody String refreshToken
    ){
        String response = jwtLogoutService.createLogoutRecordServ(accessToken,refreshToken);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(
        @RequestBody String refreshToken
    ){
        Optional<RefreshToken> refreshTokenRercord = refreshTokenService.isExistsRefreshToken(refreshToken);
        refreshTokenService.logoutRefreshToken(refreshToken);
        return authService.refreshToken(refreshTokenRercord);
    }
}
