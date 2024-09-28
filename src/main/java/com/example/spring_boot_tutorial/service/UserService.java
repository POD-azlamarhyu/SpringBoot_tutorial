package com.example.spring_boot_tutorial.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.payload.UserDTO;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;

public interface UserService {
    UserDTO getMyUser(Authentication authentication);
    
    String getMyUsername(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl);

    String getMyEmail(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl);
    
    List<User> getAllUser();
    
    Optional<User> getOneUser(UUID userId);

    String getUsernameById(UUID userId);

    String getEmailById(UUID userId);
    
    String updateLoginId(UserDetailsImpl userDetailsImpl,String loginId);

    String updateEmail(UserDetailsImpl userDetailsImpl,String email);

    String updateUsername(UserDetailsImpl userDetailsImpl,String username);

    String updatePassword(UserDetailsImpl userDetailsImpl,String password);
}
