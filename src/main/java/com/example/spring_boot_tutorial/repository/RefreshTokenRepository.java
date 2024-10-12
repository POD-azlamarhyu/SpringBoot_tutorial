package com.example.spring_boot_tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    RefreshToken findByToken(String token);

    RefreshToken findByUser(User user);
}
