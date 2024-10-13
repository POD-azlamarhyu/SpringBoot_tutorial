package com.example.spring_boot_tutorial.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.exception.UserDoesNotExistsException;
import com.example.spring_boot_tutorial.repository.RefreshTokenRepository;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.JWTTokenProvider;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;

public class RefreshTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.refreshToken.expirationMs}")
    private Long jwtRefreshExpirationDate;

    public RefreshToken getByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken getByTokenAndUser(String token){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userDetailsImpl.getId()).orElseThrow(
            () -> new UserDoesNotExistsException(userDetailsImpl.getId())
        );

        RefreshToken refreshToken = refreshTokenRepository.findByUserAndToken(user, token);
        return refreshToken;
    }

    public RefreshToken createRefreshToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userDetailsImpl.getId()).orElseThrow(
            () -> new UserDoesNotExistsException(userDetailsImpl.getId())
        );

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(jwtTokenProvider.generateJWTRefreshToken(authentication));
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationDate));
        
        RefreshToken createdRefreshToken = refreshTokenRepository.save(refreshToken);

        return createdRefreshToken;
    }

    public RefreshToken verifyRefreshTokenExpiration(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            throw new RuntimeException(refreshToken.getToken() + "\n Refresh token was expired.");
        }else{
            return refreshToken;
        }
    }
}
