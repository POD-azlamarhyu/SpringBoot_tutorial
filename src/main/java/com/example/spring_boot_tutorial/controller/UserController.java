package com.example.spring_boot_tutorial.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_tutorial.entity.User;
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

        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<?> getMyUsername(Authentication authentication){
        String username = userService.getMyUsername(authentication);
        return new ResponseEntity<>(username,HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getMyEmail(Authentication authentication){
        String email = userService.getMyEmail(authentication);
        return new ResponseEntity<>(email,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> userList = userService.getAllUser();

        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable UUID id){
        Optional<User> user = userService.getOneUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/{id}/email")
    public ResponseEntity<?> getUserEmail(@PathVariable UUID id){
        String email = userService.getEmailById(id);
        return new ResponseEntity<>(email,HttpStatus.OK);
    }

    @GetMapping("/{id}/username")
    public ResponseEntity<?> getUsername(@PathVariable UUID id){
        String username = userService.getUsernameById(id);
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @PatchMapping("/loginid")
    public ResponseEntity<String> updateLoginId(Authentication authentication,@RequestBody String loginId){
        String response = userService.updateLoginId(authentication, loginId);

        return ResponseEntity.ok().body(response);
    }
    
    @PatchMapping("/email")
    public ResponseEntity<?> updateEmail(Authentication authentication,@RequestBody String email){
        String response = userService.updateEmail(authentication, email);

        return ResponseEntity.ok().body(response);
    }
    
    @PatchMapping("/username")
    public ResponseEntity<?> updateUsername(Authentication authentication,@RequestBody String username){
        String response = userService.updateUsername(authentication, username);

        return ResponseEntity.ok().body(response);
    }
    
    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(Authentication authentication,
    @RequestBody String password){
        String respose = userService.updatePassword(authentication, password);

        return ResponseEntity.ok().body(respose);
    }
}
