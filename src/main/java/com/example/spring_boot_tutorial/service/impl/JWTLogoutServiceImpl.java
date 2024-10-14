package com.example.spring_boot_tutorial.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_boot_tutorial.entity.JWTLogout;
import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.exception.UserDoesNotExistsException;
import com.example.spring_boot_tutorial.repository.JWTLogoutRepository;
import com.example.spring_boot_tutorial.repository.RefreshTokenRepository;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.JWTLogoutService;

@Service
public class JWTLogoutServiceImpl implements JWTLogoutService {
    
    @Autowired
    JWTLogoutRepository jwtLogoutRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public boolean existsByTokenServ(String token){
        return jwtLogoutRepository.existsByToken(token);
    }

    @Override
    public boolean existsByTokenAndUserIdServ(String token , UUID userId){
        return jwtLogoutRepository.existsByTokenAndUserId(token, userId);
    }

    @Override
    @Transactional
    public String createLogoutRecordServ(String accessToken,String refreshToken){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        String token = accessToken.substring(7, accessToken.length());
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserDoesNotExistsException(userId)
        );
        JWTLogout jwtLogout = new JWTLogout();
        jwtLogout.setToken(token);
        jwtLogout.setUser(user);

        jwtLogoutRepository.save(jwtLogout);

        JWTLogout refreshLogout = new JWTLogout();
        refreshLogout.setToken(refreshToken);
        refreshLogout.setUser(user);

        jwtLogoutRepository.save(refreshLogout);
        
        return "Logout successfully.";
    }
}
