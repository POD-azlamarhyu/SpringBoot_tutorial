package com.example.spring_boot_tutorial.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.payload.UserDTO;


public interface UserService {
    UserDTO getMyUser(Authentication authentication);
    
    String getMyUsername(Authentication authentication);

    String getMyEmail(Authentication authentication);
    
    List<User> getAllUser();
    
    Optional<User> getOneUser(UUID userId);

    String getUsernameById(UUID userId);

    String getEmailById(UUID userId);
    
    String updateLoginId(Authentication authentication,String loginId);

    String updateEmail(Authentication authentication,String email);

    String updateUsername(Authentication authentication,String username);

    String updatePassword(Authentication authentication,String password);

    String deleteUser(Authentication authentication);
}
