package com.example.spring_boot_tutorial.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.exception.UserDoesNotExistsException;
import com.example.spring_boot_tutorial.payload.UserDTO;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDTO getMyUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
    public String getMyEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return userDetailsImpl.getEmail();
    }

    @Override
    public String getMyUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
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
    public String updateLoginId(String newLoginId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        String loginId = userDetailsImpl.getLoginId();
        User userRecord = userRepository.findByIdAndLoginId(userId, loginId).orElseThrow(() -> new UserDoesNotExistsException(userId));
        userRecord.setLoginId(newLoginId);
        userRepository.save(userRecord);
        
        return "updated your login id successfully";
    }

    @Override
    public String updateEmail(String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        String loginId = userDetailsImpl.getLoginId();
        User userRecord = userRepository.findByIdAndLoginId(userId, loginId).orElseThrow(() -> new UserDoesNotExistsException(userId));

        userRecord.setEmail(email);
        userRepository.save(userRecord);

        return "updated your email successfully";
    }

    @Override
    public String updateUsername(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        String loginId = userDetailsImpl.getLoginId();
        User userRecord = userRepository.findByIdAndLoginId(userId, loginId).orElseThrow(() -> new UserDoesNotExistsException(userId));

        userRecord.setUsername(username);
        userRepository.save(userRecord);

        return "Updated Your name successfully";
    }

    @Override
    public String updatePassword(String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        String loginId = userDetailsImpl.getLoginId();
        User userRecord = userRepository.findByIdAndLoginId(userId, loginId).orElseThrow(() -> new UserDoesNotExistsException(userId));
        userRecord.setPassword(passwordEncoder.encode(password));

        userRepository.save(userRecord);
        return "updated password successfully";
    }

    @Override
    public String deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetailsImpl.getId();
        User userRecord = userRepository.findById(userId).orElseThrow(
            () -> new UserDoesNotExistsException(userId)
        );
        userRepository.delete(userRecord);
        
        return "delete user successfully";
    }
}
