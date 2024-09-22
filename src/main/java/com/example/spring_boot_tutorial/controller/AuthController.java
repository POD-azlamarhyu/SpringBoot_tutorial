package com.example.spring_boot_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.payload.LoginDTO;
import com.example.spring_boot_tutorial.payload.RegisterDTO;
import com.example.spring_boot_tutorial.service.AuthService;
import com.example.spring_boot_tutorial.utils.JWTAuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    
    public AuthController(AuthService authService){
        this.authService = authService;
        
    }
    
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
}
