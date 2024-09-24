package com.example.spring_boot_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.payload.UserDTO;
import com.example.spring_boot_tutorial.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/myuser")
    public ResponseEntity<?> myUser(Authentication authentication){
        UserDTO userDTO = userService.getMyUser(authentication);

        return ResponseEntity.ok(userDTO);
    }
}
