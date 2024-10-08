package com.example.spring_boot_tutorial.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.JWTLogout;

@Repository
public interface JWTLogoutRepository extends JpaRepository<JWTLogout,Long> {
    
    Boolean existsByToken(String token);
    Boolean existsByTokenAndUserId(String token,UUID userId);
}
