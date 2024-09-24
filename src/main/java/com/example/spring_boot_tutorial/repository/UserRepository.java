package com.example.spring_boot_tutorial.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_tutorial.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByLoginIdOrEmail(String loginId,String email);
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByLoginId(String loginId);

}
