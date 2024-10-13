package com.example.spring_boot_tutorial.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.payload.UserDTO;


public interface UserService {
    UserDTO getMyUser();
    
    String getMyUsername();

    String getMyEmail();
    
    List<User> getAllUser();
    
    Optional<User> getOneUser(UUID userId);

    String getUsernameById(UUID userId);

    String getEmailById(UUID userId);
    
    String updateLoginId(String loginId);

    String updateEmail(String email);

    String updateUsername(String username);

    String updatePassword(String password);

    String deleteUser();
}
