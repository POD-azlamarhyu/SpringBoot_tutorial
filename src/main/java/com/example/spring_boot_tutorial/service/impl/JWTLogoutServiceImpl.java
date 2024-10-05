package com.example.spring_boot_tutorial.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_boot_tutorial.entity.JWTLogout;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.exception.UserDoesNotExistsException;
import com.example.spring_boot_tutorial.repository.JWTLogoutRepository;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.JWTLogoutService;

@Service
public class JWTLogoutServiceImpl implements JWTLogoutService {
    
    @Autowired
    JWTLogoutRepository jwtLogoutRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean existsByTokenServ(String token){
        return jwtLogoutRepository.existsByToken(token);
    }

    @Override
    public boolean existsByTokenAndUserIdServ(String token , UUID userId){
        return jwtLogoutRepository.existsByTokenAndUserId(token, userId);
    }

    @Override
    public String createLogoutRecordServ(UserDetailsImpl userDetailsImpl, String authToken){
        UUID userId = userDetailsImpl.getId();
        String token = authToken.substring(7, authToken.length());
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserDoesNotExistsException(userId)
        );
        JWTLogout jwtLogout = new JWTLogout();
        jwtLogout.setToken(token);
        jwtLogout.setUser(user);

        jwtLogoutRepository.save(jwtLogout);
        
        return "Logout successfully.";
    }
}
