package com.example.spring_boot_tutorial.service;

import org.springframework.security.core.Authentication;

import com.example.spring_boot_tutorial.payload.UserDTO;

public interface UserService {
    UserDTO getMyUser(Authentication authentication);
}
