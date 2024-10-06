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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.payload.UserDTO;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.authentication;
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
    public ResponseEntity<?> getMyUsername(Authentication authentication){
        String username = userServiceImpl.getMyUsername(authentication);
        return new ResponseEntity<>(username,HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getMyEmail(Authentication authentication){
        String email = authentication.getEmail();
        return new ResponseEntity<>(email,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> userList = userServiceImpl.getAllUser();

        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable UUID id){
        Optional<User> user = userServiceImpl.getOneUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/{id}/email")
    public ResponseEntity<?> getUserEmail(@PathVariable UUID id){
        String email = userServiceImpl.getEmailById(id);
        return new ResponseEntity<>(email,HttpStatus.OK);
    }

    @GetMapping("/{id}/username")
    public ResponseEntity<?> getUsername(@PathVariable UUID id){
        String username = userServiceImpl.getUsernameById(id);
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @PatchMapping("/loginid")
    public ResponseEntity<String> updateLoginId(Authentication authentication,@RequestBody String loginId){
        String response = userServiceImpl.updateLoginId(authentication, loginId);

        return ResponseEntity.ok().body(response);
    }
    
    @PatchMapping("/email")
    public ResponseEntity<?> updateEmail(Authentication authentication,@RequestBody String email){
        String response = userServiceImpl.updateEmail(authentication, email);

        return ResponseEntity.ok().body(response);
    }
    
    @PatchMapping("/username")
    public ResponseEntity<?> updateUsername(Authentication authentication,@RequestBody String username){
        String response = userServiceImpl.updateUsername(authentication, username);

        return ResponseEntity.ok().body(response);
    }
    
    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(Authentication authentication,
    @RequestBody String password){
        String respose = userServiceImpl.updatePassword(authentication, password);

        return ResponseEntity.ok().body(respose);
    }
}
