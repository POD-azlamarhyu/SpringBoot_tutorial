package com.example.spring_boot_tutorial.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.payload.UserDTO;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.UserService;
import com.example.spring_boot_tutorial.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/myuser")
    public ResponseEntity<?> myUser(Authentication authentication){
        UserDTO userDTO = userServiceImpl.getMyUser(authentication);

        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<?> getMyUsername(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        String username = userDetailsImpl.getUsername();
        return new ResponseEntity<>(username,HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getMyEmail(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        String email = userDetailsImpl.getEmail();
        return new ResponseEntity<>(email,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> userList = userServiceImpl.getAllUser();

        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable("id") UUID id){
        Optional<User> user = userServiceImpl.getOneUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
