package com.example.spring_boot_tutorial.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.exception.UserDoesNotExistsException;
import com.example.spring_boot_tutorial.payload.UserDTO;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO getMyUser(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        
        UserDTO responseBody = new UserDTO(userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),userDetails.getLoginId(),roles);
        return responseBody;
    }
    
    @Override
    public List<User> getAllUser(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> getOneUser(UUID userId){
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    @Override
    public String getMyEmail(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return userDetailsImpl.getEmail();
    }

    @Override
    public String getMyUsername(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return userDetailsImpl.getUsername();
    }

    @Override
    public String getEmailById(UUID userId){
        String email = userRepository.getEmailById(userId);
        return email;
    }

    @Override
    public String getUsernameById(UUID userId){
        String username = userRepository.getUsernameById(userId);
        return username;
    }

    @Override
    public String updateLoginId(UUID userId,String loginId){
        User userRecord = userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExistsException(userId));
        userRecord.setLoginId(loginId);
        userRepository.save(userRecord);
        

        return "update your login id successfully";
    }
}
