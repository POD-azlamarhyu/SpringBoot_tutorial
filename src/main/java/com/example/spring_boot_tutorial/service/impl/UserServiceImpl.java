package com.example.spring_boot_tutorial.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;

import com.example.spring_boot_tutorial.payload.UserDTO;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public UserDTO getMyUser(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        
        UserDTO responseBody = new UserDTO(userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),userDetails.getLoginId(),roles);
        return responseBody;
    }
    
}
