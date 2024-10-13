package com.example.spring_boot_tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.entity.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    RefreshToken findByToken(String token);

    RefreshToken findByUser(User user);

    RefreshToken findByUserAndToken(User user,String token);
}
